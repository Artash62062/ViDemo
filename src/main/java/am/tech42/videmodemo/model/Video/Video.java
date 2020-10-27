package am.tech42.videmodemo.model.Video;

import am.tech42.videmodemo.model.Actions.UserActions;
import am.tech42.videmodemo.model.CommentsAndReactions.Comments;
import am.tech42.videmodemo.model.CommentsAndReactions.Reactions;
import am.tech42.videmodemo.model.User.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.aspectj.util.FileUtil;

import javax.persistence.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int views;
    private String src;
    private String photoSrc;
    private String description;

    @ElementCollection(targetClass = VideoCategory.class)
    @CollectionTable(name = "Video_Category", joinColumns = @JoinColumn(name = "video_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "category_name")
    private Set<VideoCategory> CategorySet;

    @OneToMany(mappedBy = "video")
    List<Reactions> reactions;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    @OneToMany(mappedBy = "video")
    List<UserActions> userActions;

    @OneToMany(mappedBy = "video")
    List<Comments> comments;

    public Set<VideoCategory> getCategorySet() {
        return CategorySet;
    }

    public void setCategorySet(Set<VideoCategory> categorySet) {
        CategorySet = categorySet;
    }

    public List<UserActions> getUserActions() {
        return userActions;
    }

    public void setUserActions(List<UserActions> userActions) {
        this.userActions = userActions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Reactions> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reactions> reactions) {
        this.reactions = reactions;
    }

    public String getPhotoSrc() {
        return photoSrc;
    }

    public String getPhoto() throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(photoSrc));
        return Base64.getEncoder().encodeToString(fileContent);
    }


    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
