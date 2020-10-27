package am.tech42.videmodemo.controllers.UserController;

import am.tech42.videmodemo.model.Actions.UserActions;
import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.model.Video.Video;
import am.tech42.videmodemo.services.ActionService;
import am.tech42.videmodemo.services.UserService;
import am.tech42.videmodemo.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/profile")
public class profileController {
    @Autowired
    UserService userService;
    @Autowired
    VideoService videoService;
    @Autowired
    ActionService actionService;

    @GetMapping()
    public String showProfileActions(Model model) {
        IsAuthenticated.isUserAuthenticated(model);
        boolean isThisUser = true;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserActions> actions = actionService.getUserActions(user);
        model.addAttribute("user",user);
        model.addAttribute("actions",actions);
        return "UserPages/UserProfileActions";
    }

    @GetMapping("/videos")
    public String showProfileVideos(Model model) {
        IsAuthenticated.isUserAuthenticated(model);
        boolean isThisUser = true;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Video> videos = videoService.getUserVideos(user);
        model.addAttribute("user",user);
        model.addAttribute("videos",videos);
        return "UserPages/UserProfileVideos";
    }

}