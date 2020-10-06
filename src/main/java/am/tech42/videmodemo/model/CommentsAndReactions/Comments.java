package am.tech42.videmodemo.model.CommentsAndReactions;

import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Video.Video;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comments {
    @Id
    int id;
    @ManyToOne
    User user;
    @ManyToOne
    Video video;

    String comment;
}
