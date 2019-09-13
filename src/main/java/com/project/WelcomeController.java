package com.project;

import com.project.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WelcomeController {

    private UserRepository userRepository;

    public WelcomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        StringBuilder response = new StringBuilder();
        this.userRepository.findAll().forEach(a -> response.append(a).append("<br/>"));
        model.addAttribute("response", response);
        return "index";
    }
}
