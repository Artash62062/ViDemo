package am.tech42.videmodemo.configs;

import am.tech42.videmodemo.model.User;
import am.tech42.videmodemo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User getOne(String mail) {
        return userRepo.findByMail(mail);
    }

    @Transactional
    public void add(User user){
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
}
