package am.tech42.videmodemo.repositories;

import am.tech42.videmodemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <User,Integer> {
    User findByMail(String mail);
}
