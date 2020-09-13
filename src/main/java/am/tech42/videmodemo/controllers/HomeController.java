package am.tech42.videmodemo.controllers;

import am.tech42.videmodemo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.jws.soap.SOAPBinding;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home (Model model){

        model.addAttribute("title","Главная Страница");
        return "redirect:/videos";
    }
}
