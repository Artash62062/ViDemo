package am.tech42.videmodemo.model;

import am.tech42.videmodemo.model.CommentsAndReactions.Comments;
import am.tech42.videmodemo.model.CommentsAndReactions.Reactions;

import javax.persistence.*;
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

    public Boolean isBaned() {
        return isBaned;
    }

    public void setBaned(Boolean baned) {
        isBaned = baned;
    }
}

