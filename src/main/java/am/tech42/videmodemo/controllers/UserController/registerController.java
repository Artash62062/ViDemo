package am.tech42.videmodemo.controllers.UserController;

import am.tech42.videmodemo.services.UserService;
import am.tech42.videmodemo.model.User.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class registerController {
    final
    UserService userService;

    public registerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model){
        return "AuthenticationPages/register";
    }

    @PostMapping("/register")
    public String addUser(User user, Model model){
        User userFromDb = userService.getOnebyEmail(user.getMail());
        int userExistCode;
        if(userFromDb!= null) {
            userExistCode=1;
            model.addAttribute("UserExistCode",userExistCode );
            return "AuthenticationPages/register";
        }
        userFromDb = userService.getOneByName(user.getName());
        if(userFromDb!=null) {
            userExistCode=2;
            model.addAttribute("UserExistCode",2);
            return "AuthenticationPages/register";
        }
        userService.add(user);
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority grantedAuthority= (GrantedAuthority) user::getRole;
        authorities.add(grantedAuthority);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user,null,authorities));
        return "redirect:/" ;
    }
}
