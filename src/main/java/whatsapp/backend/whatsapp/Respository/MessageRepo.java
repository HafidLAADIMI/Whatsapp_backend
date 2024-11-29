package whatsapp.backend.whatsapp.Respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import whatsapp.backend.whatsapp.Models.Message;

@Repository
public interface MessageRepo extends MongoRepository<Message, String> {
    Iterable<Message> findBySenderId(String senderId);

    Iterable<Message> findByReceiverId(String receiverId);

    Iterable<Message> findByReceiverIdAndSenderId(String receiverId, String senderId);

    @Query("{ '$or': [ { '$and': [ { 'senderId': ?0 }, { 'receiverId': ?1 } ] }, { '$and': [ { 'senderId': ?1 }, { 'receiverId': ?0 } ] } ] }")
    Iterable<Message> findChatMessages(String senderId, String receiverId);


}
