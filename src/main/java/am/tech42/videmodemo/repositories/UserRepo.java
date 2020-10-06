package am.tech42.videmodemo.repositories;

import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <User,Integer> {
    User findByMail(String mail);
    User findById(int id);
    User findByName(String name);
    User findByVideosIsContaining(Video video);

}
