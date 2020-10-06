package am.tech42.videmodemo.controllers.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {
    @GetMapping("/login")
    public String signUp(@RequestParam(name = "error",required = false) Boolean error, Model model) {
        if(Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        IsAuthenticated.isUserAuthenticated(model);
        return "AuthenticationPages/login";
    }
}
