package com.project;

import com.project.entity.Professor;
import com.project.entity.Student;
import com.project.entity.UserDetails;
import com.project.repository.*;
import com.project.service.AssignmentService;
import com.project.service.ProfessorService;
import com.project.service.StudentService;
import com.project.service.SubjectService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class WelcomeController {

    private final SubjectService subjectService;
    private final AssignmentService assignmentService;

    private final ProfessorService professorService;

    private final StudentService studentService;
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
            , SubjectService subjectService, AssignmentService assignmentService, ProfessorService professorService, StudentService studentService) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
        this.subjectService = subjectService;
        this.assignmentService = assignmentService;
        this.professorService = professorService;
        this.studentService = studentService;
    }
//
//    public WelcomeController(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }


    @RequestMapping("/")
    public String index(Model model, Authentication authentication, HttpSession session) {
        List<GrantedAuthority> auths = new ArrayList<>(authentication.getAuthorities());
        UserDetails details = (UserDetails) authentication.getPrincipal();
        if (auths.stream().anyMatch(a -> a.getAuthority().equals("ROLE_PROFESSOR"))) {
            Professor professor = professorService.getProfessor(details.getUsername());
            System.out.println(professor.getSubjects());
            session.setAttribute("professor_subjects", professor.getSubjects());
        }
        if (auths.stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {
            Student student = studentService.getStudent(details.getUsername());
            session.setAttribute("student_courses", student.getCourses());
            session.setAttribute("student",student);
            System.out.println(student.getCourses());
        }

        return "index";
    }

}
