package am.tech42.videmodemo.services;

import am.tech42.videmodemo.model.Actions.ActionsValue;
import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Video.Video;
import am.tech42.videmodemo.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.IOUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class VideoService {
    String UPLOADED_FOLDER="/home/artash/Documents/ViDemoVideos/Videos/";
    @Autowired
    private VideoRepository videoRepo;
    @Autowired
    private ActionService actionService;

    public List<Video> getAll() {
        return videoRepo.findAll();
    }

    public List<Video> getUserVideos(User user) {
        return videoRepo.findAllByUser(user);
    }

    public List<Video> searchVideos(String search) {
        return videoRepo.findAllByNameIsLike(search);
    }
    public Video getById(int id) { return videoRepo.findById(id) ; }



    @Transactional
    public Boolean addVideo(MultipartFile video, MultipartFile VideoPicture,String VideoName,String Description,User LogedInUser) {

        String directoryName = UPLOADED_FOLDER +"/"+ LogedInUser.getName();
        File DriectoryFolder = new File(directoryName);
        DriectoryFolder.mkdir();
        directoryName = directoryName+"/"+VideoName;
        DriectoryFolder = new File(directoryName);
        DriectoryFolder.mkdir();
        directoryName = directoryName+"/";
        try {
            Path VideoPath = Paths.get(directoryName + video.getOriginalFilename());
            Path PicturePath = Paths.get(directoryName + VideoPicture.getOriginalFilename());
            byte[] PictureBytes = VideoPicture.getBytes();
            Files.write(PicturePath,PictureBytes);
            Files.copy(video.getInputStream(),VideoPath, StandardCopyOption.REPLACE_EXISTING);
            Video newVideo = new Video();
            newVideo.setName(VideoName);
            newVideo.setDescription(Description);
            newVideo.setUser(LogedInUser);
            newVideo.setName(VideoName);
            newVideo.setSrc(VideoPath.toString());
            newVideo.setPhotoSrc(PicturePath.toString());
            newVideo.setViews(0);
            videoRepo.save(newVideo);
            actionService.addAction(LogedInUser,newVideo,"Added The Video");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Transactional
    public void deleteVideo(Video video){ videoRepo.delete(video);}
}
