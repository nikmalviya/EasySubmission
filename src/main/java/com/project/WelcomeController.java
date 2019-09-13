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
//        Course course = new Course("Java Master Class");
//        course.setStudents(new ArrayList<>());
//        course.getStudents().add(new Student("Nikhil", null, "9999999999", "helloworld"));
//        course.getStudents().add(new Student("Nikhil Malviya", null, "9999999999", "helloworld"));
//        course.getStudents().add(new Student("nikmalviya", null, "9999999999", "helloworld"));
//        courseRepository.save(course);

//        studentRepository.deleteById(3);
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
