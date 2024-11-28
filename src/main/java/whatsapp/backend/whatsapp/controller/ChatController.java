package whatsapp.backend.whatsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import whatsapp.backend.whatsapp.Models.Message;
import whatsapp.backend.whatsapp.Models.User;
import whatsapp.backend.whatsapp.Service.MessageService;
import whatsapp.backend.whatsapp.Service.UserService;

@Controller
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessageTemplate;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;


    // public message controllers
//    @MessageMapping("/public-message") // the client should listen to  "/app/public-message"
//    @SendTo("/public/chatRoom")
//    public Message sendMessgae(@Payload Message message) {
//        System.out.println("message = " + message);
//        return message;
//    }


    // private message controllers
    @MessageMapping("/private")
    public void sendPrivateMessage(@Payload Message message) {
        // Send the message to the specific user
        simpMessageTemplate.convertAndSendToUser(
                message.getReceiverId(), // Receiver's unique identifier
                "/private", // Destination suffix
                message // Message payload
        );

        // Log for debugging
        System.out.println("Sent private message to user: " + message.getReceiverId());
        System.out.println("Message details: " + message);
    }

    // adding a new message
    @PostMapping("/message/send")
    public ResponseEntity<?> sendMessageViaRest(@RequestBody Message message) {
        try {
            simpMessageTemplate.convertAndSendToUser(message.getReceiverId(), "/private", message);
            messageService.addMessage(message);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error sending message");
        }
    }

    @PostMapping("/user/add")
    public ResponseEntity<?> addUser(@RequestPart("username") String username, @RequestPart("number") String number, @RequestPart("id") String id,@RequestPart("profile") String profile) {
        try {
            System.out.println("Username: " + username);
            System.out.println("Number: " + number);
            System.out.println("ID: " + id);
//            System.out.println("Profile: " + profile.getOriginalFilename()) || profile.getBytes(), profile.getContentType();
            userService.addUser(new User(id, username, number,profile));
            if (username == null || number == null || id == null) {
                return new ResponseEntity<>("You have to provide all the user information", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("User added successfully", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("There was an error in adding the user", HttpStatus.BAD_REQUEST);
        }
    }

    // get all users
    @GetMapping("/user/getAll")
    public ResponseEntity<?> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("There was an error in getting the users", HttpStatus.BAD_REQUEST);
        }
    }

    // get message by receiverId
    @GetMapping("/message/getByReceiverId/{receiverId}")
    public ResponseEntity<?> getMessagesByReceiverId(@PathVariable String receiverId) {
        try {
            return new ResponseEntity<>(messageService.findMessagesByReceiverId(receiverId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("There was an error in getting the messages", HttpStatus.BAD_REQUEST);
        }
    }

}

