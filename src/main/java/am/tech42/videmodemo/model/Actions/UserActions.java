package am.tech42.videmodemo.model.Actions;

import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Video.Video;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    
    @Column(name = "action_value")
    private String actionsValue;

    private LocalDateTime localDateTime;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

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

    public String getActionsValue() {
        return actionsValue;
    }

    public void setActionsValue(String actionsValue) {
        this.actionsValue = actionsValue;
    }
}
