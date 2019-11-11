package com.project;

import com.project.entity.Professor;
import com.project.entity.Student;
import com.project.entity.UserDetails;
import com.project.repository.CourseRepository;
import com.project.repository.ProfessorRepository;
import com.project.repository.SubjectRepository;
import com.project.repository.UserRepository;
import com.project.service.ProfessorService;
import com.project.service.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class WelcomeController {

    private UserRepository userRepository;
    private StudentService studentService;
    private CourseRepository courseRepository;
    private ProfessorRepository professorRepository;
    private SubjectRepository subjectRepository;
    private ProfessorService professorService;

    public WelcomeController(UserRepository userRepository,
                             ProfessorRepository professorRepository,
                             StudentService studentService,
                             CourseRepository courseRepository,
                             SubjectRepository subjectRepository,
                             ProfessorService professorService
    ) {
        this.userRepository = userRepository;
        this.studentService = studentService;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
        this.professorService = professorService;
    }
//
//    public WelcomeController(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }


    @RequestMapping("/")
    public String index(Model model, Authentication authentication, HttpSession session, SessionStatus status) {
        List<GrantedAuthority> auths = new ArrayList<>(authentication.getAuthorities());
        UserDetails details = (UserDetails) authentication.getPrincipal();
        if (auths.stream().anyMatch(a -> a.getAuthority().equals("ROLE_PROFESSOR"))) {
            Professor professor = professorService.getProfessor(details.getUsername());
            System.out.println(professor.getSubjects());
            session.setAttribute("professor_subjects", professor.getSubjects());
        }
        if(auths.stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))){
            Student student = studentService.getStudent(details.getUsername());
            session.setAttribute("student_courses", student.getCourses());
            session.setAttribute("student",student);
            System.out.println(student.getCourses());
        }

        return "index";
    }

}
