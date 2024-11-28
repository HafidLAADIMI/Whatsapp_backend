package whatsapp.backend.whatsapp.configuration;


import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import whatsapp.backend.whatsapp.Models.Message;
import whatsapp.backend.whatsapp.Models.Status;

// this is will the class to listen if a client leave or join the chat
public class WebSocketEventListenner {

    // this is an interface that provides a method to send messages directly to a specified destination in the application allowing the messages to be broadcasted or sent to private destination in the application

    private SimpMessageSendingOperations messageSendingOperations;
    @EventListener // this annotation allows the class to listen if a client leave or join the chat
    public void handleDisconnectEventListenner (SessionDisconnectEvent event ) {
        // this is a class that give information about websocket session
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        // this is the username of the client , that already stored in the header when the user has joined the chat
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        // here if we found a username which means that the user has left the chat we will build a new chat model and send it to the other users via messageSendingOperations b
        if(username != null) {
                  // here we are going to build a new chat model to inform other user that the user has left the chat
                  Message chatModel = Message.builder().senderId(username).message("has left the chat").status(Status.leave).build();
                  messageSendingOperations.convertAndSend("/public", chatModel);
        }

    }

}

