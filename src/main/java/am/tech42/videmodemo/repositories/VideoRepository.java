package am.tech42.videmodemo.repositories;

import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Actions.UserActions;
import am.tech42.videmodemo.model.Video.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoRepository extends CrudRepository <Video,Integer> {
    List<Video> findAllByUser(User user);
    List<Video> findAllByNameIsLike(String string);
    List<Video> findAll();
    Video findByUserActions(UserActions userActions);
    Video findById(int id);
}
