package am.tech42.videmodemo.controllers.VideoControllers;

import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Video.Video;
import am.tech42.videmodemo.services.UserService;
import am.tech42.videmodemo.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class EditVideosController {
    @Autowired
    UserService userService;
    @Autowired
    VideoService videoService;

    @GetMapping("/profile/editvideos")
    private String editVideos(Model model){
        User logedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Video> videos = videoService.getUserVideos(logedInUser);
        return"/editvideos";
    }

    @GetMapping("/profile/editvideos/delete")
    public String deleteVideo(@RequestParam (name = "id") int id,Model model){
        Video deletedVideo = videoService.getById(id);
        videoService.deleteVideo(deletedVideo);
        model.addAttribute("succes",true);
        return "redirect:/profile/editVideos";
    }


    @GetMapping("/profile/editvideos/upload")
    public String index() {
            return "VideoPages/upload";
        }


    @PostMapping("/profile/editvideos/upload")
    public String MultyFileUpload(MultipartFile video, MultipartFile VideoPicture,String VideoName,String Description,Model model) {
        if (video.isEmpty()||VideoPicture.isEmpty()) {
            return "redirect:uploadStatus";
        }
        User LogedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("VideoUploadSuccess",videoService.addVideo(video,VideoPicture,VideoName,Description,LogedInUser));
        return "VideoPages/upload";
    }

        @GetMapping("/uploadStatus")
        public String uploadStatus() {
            return "VideoPages/uploadStatus";
        }


}
