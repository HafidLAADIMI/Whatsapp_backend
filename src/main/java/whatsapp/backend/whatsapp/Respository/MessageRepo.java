package whatsapp.backend.whatsapp.Respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import whatsapp.backend.whatsapp.Models.Message;

@Repository
public interface MessageRepo extends MongoRepository<Message, String> {
    Iterable<Message> findBySenderId(String senderId);
    Iterable<Message> findByReceiverId(String receiverId);
}
