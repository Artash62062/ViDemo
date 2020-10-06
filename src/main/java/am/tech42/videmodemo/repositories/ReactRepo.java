package am.tech42.videmodemo.repositories;

import am.tech42.videmodemo.model.CommentsAndReactions.Reactions;
import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactRepo extends JpaRepository<Reactions,Integer> {
    List<Reactions> findAllByVideo(Video video);
    List<Reactions> findAllByUser (User user);
}
