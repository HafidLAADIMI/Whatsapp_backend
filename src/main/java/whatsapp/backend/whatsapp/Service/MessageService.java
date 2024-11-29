package whatsapp.backend.whatsapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whatsapp.backend.whatsapp.Models.Message;
import whatsapp.backend.whatsapp.Respository.MessageRepo;

@Service
public class MessageService {
    @Autowired
    private MessageRepo repo;

    // adding message to the database
    public void addMessage(Message message) {
        repo.save(message);
    }

    // finding messages by senderId
    public Iterable<Message> findMessagesBySender(String senderId) {
        return repo.findBySenderId(senderId);
    }

    // find messages by receiverId
    public Iterable<Message> findMessagesByReceiverId(String receiverId) {
        return repo.findByReceiverId(receiverId);
    }

    public Iterable<Message> findMessagesByReceiverIdAndSenderId(String receiverId, String senderId) {
        return repo.findByReceiverIdAndSenderId(receiverId, senderId);
    }

    public Iterable<Message> findChatMessage(String senderId, String receiverId) {
        return repo.findChatMessages(senderId, receiverId);
    }
}
