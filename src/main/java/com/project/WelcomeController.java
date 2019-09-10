package com.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class WelcomeController {

    private final StudentRepository studentRepository;

    public WelcomeController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @RequestMapping("/")
    public String index(){

        this.studentRepository.findAll().forEach(System.out::println);
        return "index";
    }
}
