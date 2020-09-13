package am.tech42.videmodemo.repositories;

import am.tech42.videmodemo.model.Reactions;
import am.tech42.videmodemo.model.User;
import am.tech42.videmodemo.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactRepo extends JpaRepository<Reactions,Integer> {
    List<Reactions> findAllByVideo(Video video);
    List<Reactions> findAllByUser (User user);
}
