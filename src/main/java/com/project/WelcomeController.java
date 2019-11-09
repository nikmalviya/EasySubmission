package com.project;

import com.project.entity.User;
import com.project.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/")
public class WelcomeController {

    private UserRepository userRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private ProfessorRepository professorRepository;
    private SubjectRepository subjectRepository;

    public WelcomeController(UserRepository userRepository,
                             ProfessorRepository professorRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository,
                             SubjectRepository subjectRepository
                             ) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.subjectRepository=subjectRepository;
    }
//
//    public WelcomeController(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }


    @RequestMapping("/")
    public String index(Model model) {
//        Course course = courseRepository.findByCourseTitleLike("Spring%");
//        Student student = studentRepository.findById(5).orElse(null);
//        assert student != null;
//        student.getCourses().add(course);
//        studentRepository.save(student);
//        model.addAttribute("students", this.studentRepository.findAll());
//        Course course = new Course("CBA");
//        User user = new User("mananmistry", "Sherlock", UserType.PROFESSOR, UserStatus.ACTIVE);
//        Professor professor = new Professor("Manan Mistry", null, "7096693099", "new", user);
//        courseRepository.save(course);
//        professorRepository.save(professor);
        //Test Case.
//        Course course=courseRepository.findById(1).orElse(null);
//        Professor professor=professorRepository.findById(1).orElse(null);
//        Subject subject=new Subject("DBMS",course,professor);
//        subjectRepository.save(subject);
//        course.getSubjects().forEach(System.out::println);
       return "index";
    }

}
