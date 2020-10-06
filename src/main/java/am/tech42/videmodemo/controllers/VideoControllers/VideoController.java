package am.tech42.videmodemo.controllers.VideoControllers;

import am.tech42.videmodemo.model.Video;
import am.tech42.videmodemo.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VideoController {
    @Autowired
    private VideoRepository videoRepository;


    @GetMapping("/videos")
    public String ShowVideos(Model model){ ;
        Iterable<Video> videos = videoRepository.findAll();
        model.addAttribute("videos",videos);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
        model.addAttribute("LoggedInUser",isAuthenticated);
        return"videos";
    }


}