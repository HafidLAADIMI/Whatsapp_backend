package whatsapp.backend.whatsapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whatsapp.backend.whatsapp.Models.User;
import whatsapp.backend.whatsapp.Respository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    // add User
    public void addUser(User user) {

        System.out.println("service layer "+user);
        userRepo.save(user);
    }

    // find User by Id
    public User findUserById(String id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // find all Users
    public Iterable<User> findAllUsers() {
        return userRepo.findAll();
    }
}
