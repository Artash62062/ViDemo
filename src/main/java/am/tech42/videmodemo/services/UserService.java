package am.tech42.videmodemo.services;

import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Video.Video;
import am.tech42.videmodemo.repositories.UserRepo;
import am.tech42.videmodemo.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User getOnebyEmail(String mail) {
        return userRepo.findByMail(mail);
    }

    public User getOneByName(String username){ return userRepo.findByName(username); }

    public User getOnebyId(int id){ return userRepo.findById(id); }

    public User findByVideo(Video video) {
        return video.getUser();
    }

    public User getLogedInUser (){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Transactional
    public void editUser(){

    }

    @Transactional
    public void deleteUser(User user){
        userRepo.delete(user);
    }

    @Transactional
    public void add(User user){
        user.setRole("ROLE_ADMIN");
        user.setBaned(Boolean.FALSE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
}
