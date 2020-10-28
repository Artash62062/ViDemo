package am.tech42.videmodemo.controllers.VideoControllers;


import am.tech42.videmodemo.model.Video.Video;
import am.tech42.videmodemo.repositories.VideoRepository;
import am.tech42.videmodemo.services.VideoService;
import com.sun.org.glassfish.external.statistics.annotations.Reset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("videos")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private VideoService videoService;


    @GetMapping
    public String showVideos(Model model) throws IOException {
        List <Video> videos = videoRepository.findAll();
        model.addAttribute("videos",videos);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
        model.addAttribute("LoggedInUser",isAuthenticated);
        return"VideoPages/videos";
    }

    @GetMapping("{id}")
    public String showVideo (@PathVariable int id,Model model) throws IOException {
        Video currentVideo = videoService.getById(id);
        List <Video> userVideos = videoService.getUserVideos(currentVideo.getUser());
        model.addAttribute("video",currentVideo);
        model.addAttribute("userVideos",userVideos);
        return "VideoPages/showVideo";
    }

}
