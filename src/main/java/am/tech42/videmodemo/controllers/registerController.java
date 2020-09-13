package am.tech42.videmodemo.controllers;

import am.tech42.videmodemo.configs.UserService;
import am.tech42.videmodemo.model.User;
import am.tech42.videmodemo.repositories.UserRepo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class registerController {
    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
        model.addAttribute("LoggedInUser",isAuthenticated);
        return "register";
    }

    @PostMapping("/register")
    public String addUser(User user, Model model){
        User userFromDb = userService.getOne(user.getMail());
        if(userFromDb!= null) {
            model.addAttribute("message", "User exist");
            return "redirect:/login";
        }
       // model.addAttribute("LoggedInUser",UserService.isUserLoggedIn());
        user.setRole("ROLE_ADMIN");
        userService.add(user);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
        model.addAttribute("LoggedInUser",isAuthenticated);
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority grantedAuthority= new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        };
        authorities.add(grantedAuthority);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user,null,authorities));
        return "redirect:/" ;
    }
}
