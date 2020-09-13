package am.tech42.videmodemo.repositories;

import am.tech42.videmodemo.model.User;
import am.tech42.videmodemo.model.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoRepository extends CrudRepository <Video,Integer> {
    List<Video> findAllByUser(User user);
    List<Video> findAll();


}
