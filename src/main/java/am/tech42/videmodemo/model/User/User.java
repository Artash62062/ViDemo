package am.tech42.videmodemo.model.User;

import am.tech42.videmodemo.model.Actions.UserActions;
import am.tech42.videmodemo.model.CommentsAndReactions.Comments;
import am.tech42.videmodemo.model.CommentsAndReactions.Reactions;
import am.tech42.videmodemo.model.Video.Video;
import org.apache.commons.io.FileUtils;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String mail;
    private String name;
    private String password;
    private String role;
    private String profPicSrc;

    public String getProfPicSrc() {
        return profPicSrc;
    }

    public void setProfPicSrc(String profPicSrc) {
        this.profPicSrc = profPicSrc;
    }

    public Boolean getBaned() {
        return isBaned;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<UserActions> getActions() {
        return actions;
    }

    public void setActions(List<UserActions> actions) {
        this.actions = actions;
    }

    public List<Reactions> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reactions> reactions) {
        this.reactions = reactions;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @Column(name = "is_baned")
    private Boolean isBaned;

    @OneToMany(mappedBy = "user")
    private List<Video> videos;

    @OneToMany(mappedBy = "user")
    private List<UserActions> actions;

    @OneToMany(mappedBy = "user")
    private List<Reactions> reactions;

    @OneToMany(mappedBy = "user")
    private List<Comments> comments;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String profPic() throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(profPicSrc));
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public Boolean isBaned() {
        return isBaned;
    }

    public void setBaned(Boolean baned) {
        isBaned = baned;
    }
}

