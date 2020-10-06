package am.tech42.videmodemo.controllers.UserController;

import am.tech42.videmodemo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class adminController {
    @Autowired
    UserRepo userRepo;
    @GetMapping("/admin")
    public String adminPage() {


        return "admin";
    }

    @GetMapping("/admin/users")
    public String admingetusers(){
        if(chackisAdmin()==false) {
            return "redirect:/";
        }
        return "UserPages/users";
    }

    private boolean chackisAdmin(){
        boolean isAdmin = false;
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        List <GrantedAuthority> authorities= (List<GrantedAuthority>) authentication.getAuthorities();
        for (GrantedAuthority authority:authorities){
            if((authority.getAuthority().equals("ROLE_ADMIN"))){
                isAdmin = true;
            }
        }
        return isAdmin;
    }

}
