package am.tech42.videmodemo.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "reactions",uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","video_id"}))
public class Reactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;
    @ManyToOne(fetch = FetchType.LAZY)
    Video video;
    @Enumerated(value = EnumType.STRING)
    ReactionsValue reactionsValue;

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

    public ReactionsValue getReactionsValue() {
        return reactionsValue;
    }

    public void setReactionsValue(ReactionsValue reactionsValue) {
        this.reactionsValue = reactionsValue;
    }
}
