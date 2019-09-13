package com.project;

import com.project.repository.StudentRepository;
import com.project.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WelcomeController {


    private UserRepository userRepository;

    private StudentRepository studentRepository;

    public WelcomeController(UserRepository userRepository,
                             StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }
//
//    public WelcomeController(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }

    @RequestMapping("/")
    public String index(Model model) {
//        User newUser = new User("helloworld11", "helloworld", UserType.PROFESSOR, UserStatus.ACTIVE);
//        Student student = new Student("Nikhil Malviya", null, "99999", "Helloworld");
//        student.setUser(newUser);
//        studentRepository.save(student);
//        this.userRepository.save(newUser);
//        studentRepository.deleteById(1);
        studentRepository.deleteById(3);

        StringBuilder response = new StringBuilder();
        this.studentRepository.findAll().forEach(a -> response.append(a).append("<br/>"));
        model.addAttribute("response", response);
        return "index";
    }
}
