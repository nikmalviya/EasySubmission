package com.project;

import com.project.entity.Course;
import com.project.entity.Student;
import com.project.repository.CourseRepository;
import com.project.repository.StudentRepository;
import com.project.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WelcomeController {


    private UserRepository userRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public WelcomeController(UserRepository userRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    @RequestMapping("/")
    public String index(Model model) {
        Course course = courseRepository.findByCourseTitleLike("Spring%");
        Student student = studentRepository.findById(5).orElse(null);
        assert student != null;
        student.getCourses().add(course);
        studentRepository.save(student);
        StringBuilder response = new StringBuilder();
        this.studentRepository.findAll().forEach(a -> response.append(a).append("<br/>"));
        model.addAttribute("response", response);
        return "index";
    }
}
