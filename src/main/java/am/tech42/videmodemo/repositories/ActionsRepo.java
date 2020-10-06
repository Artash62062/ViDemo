package am.tech42.videmodemo.repositories;

import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Actions.UserActions;
import am.tech42.videmodemo.model.Video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionsRepo extends JpaRepository<UserActions,Integer> {
    List<UserActions> findAllByUser(User user);
    List<UserActions> findAllByVideo(Video video);

}
