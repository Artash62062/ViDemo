package am.tech42.videmodemo.model.Actions;

import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Video.Video;

import javax.persistence.*;

@Table(name="user_actions")
@Entity
public class UserActions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Video video;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_value")
    private ActionsValue actionsValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public ActionsValue getActionsValue() {
        return actionsValue;
    }

    public void setActionsValue(ActionsValue actionsValue) {
        this.actionsValue = actionsValue;
    }
}
