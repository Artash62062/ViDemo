package am.tech42.videmodemo.repositories;

import am.tech42.videmodemo.model.Actions.UserActions;
import am.tech42.videmodemo.model.CommentsAndReactions.Comments;
import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepo extends JpaRepository<Comments,Integer> {
    List<Comments> findAllByVideo(Video video);
    List<Comments> findAllByUser(User user);

}
