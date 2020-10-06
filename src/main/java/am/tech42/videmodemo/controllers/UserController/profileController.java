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
public class profileController {
    @Autowired
    UserService userService;
    @Autowired
    VideoService videoService;
    @Autowired
    ActionService actionService;


    @GetMapping( path = {"/profile/{username}","/profile","/profile/{username}/{contentType}"})
    public String showProfile(@PathVariable (required = false,name = "username") String username,
                              @PathVariable (required = false,name = "contentType") String contentType,
                              @RequestParam(required = false,name = "error") String error,
                                      Model model )
    {
        IsAuthenticated.isUserAuthenticated(model);
        User wantedUser = new User();
        boolean isThisUser =false;
        if (username!= null&& !(username.equals("error")) &&!(username.equals("activity")|| username.equals("videos"))){
            User userFromDb = userService.getOneByName(username);
            if(userFromDb == null) {
                return "redirect:/profile?error=notfound";
            }
            isThisUser=false;
            wantedUser=userFromDb;
        } else if(error!= null){
            model.addAttribute("isNotUserExist",true);
            return "UserPages/UserProfile";
        }
        else {
            wantedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isThisUser = true;
        }
        if(username==null) {
            return "redirect:/profile/"+ wantedUser.getName();
        }
        if(contentType==null && username.equals("activity")){
        return "redirect:/profile/"+ wantedUser.getName() + "/activity";
        }

        if(contentType==null){
            return "redirect:/profile/"+username+"/activity";
        }

        model.addAttribute("contextType",contentType);
        model.addAttribute("user",wantedUser);
        model.addAttribute("isAuthenticatedUser",isThisUser);
        if(contentType.equals("activity")) {
            List <UserActions> userActionsList = actionService.getUserActions(wantedUser);
            model.addAttribute("actions", userActionsList);
        }else if(contentType.equals("videos")) {
            List<Video> userVideos = videoService.getUserVideos(wantedUser);
            model.addAttribute("uservideos",userVideos);
        }
            return "UserPages/UserProfile";
    }
}

