package whatsapp.backend.whatsapp.Respository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;
import whatsapp.backend.whatsapp.Models.User;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    //find message by sender
    Optional<User> findById(String id);
}
