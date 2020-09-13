package am.tech42.videmodemo.controllers;

import am.tech42.videmodemo.configs.UserService;
import am.tech42.videmodemo.model.User;
import am.tech42.videmodemo.security.AuthentificationProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {
    @GetMapping("/login")
    public String signUp(@RequestParam(name = "error",required = false) Boolean error, Model model) {
        if(Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
        model.addAttribute("LoggedInUser",isAuthenticated);
        return "login";
    }
}
