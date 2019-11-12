package com.project;

import com.project.entity.Professor;
import com.project.entity.Student;
import com.project.entity.UserDetails;
import com.project.repository.*;
import com.project.service.*;
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
    final private SubmissionService submissionService;

    public WelcomeController(UserRepository userRepository,
                             ProfessorRepository professorRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository,
                             SubjectRepository subjectRepository,
                             SubjectService subjectService,
                             AssignmentService assignmentService,
                             ProfessorService professorService,
                             StudentService studentService,
                             SubmissionService submissionService) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
        this.subjectService = subjectService;
        this.assignmentService = assignmentService;

        this.studentService = studentService;


        this.professorService = professorService;

        this.submissionService = submissionService;
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
            session.setAttribute("professor_subjects", professor.getSubjects());
        }
        if (auths.stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {
            Student student = studentService.getStudent(details.getUsername());
            session.setAttribute("student_courses", student.getCourses());
            session.setAttribute("student", student);
        }



        //    Course course = courseRepository.findByCourseTitleLike("Spring%");
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
//        Subject subject=this.subjectService.getSubject(2);
//        System.out.println(subject.getSubjectName());
//        Assignment assignment=new Assignment("new assignment",new Date(),new Date(),subject,true,"helloworld","path");
//        subject.addAssignment(assignment);
////        assignment.setSubject(subject);
//        this.assignmentService.saveAssignment(assignment);
//        subjectService.saveSubject(subject);

//        Assignment assignment=this.assignmentService.getAssignment(2);
//        Student student=this.studentService.getStudent(7);
//        Subject subject=this.subjectService.getSubject(2);
//        Submission submission=new Submission(assignment,subject,student,"status",Float.valueOf("10.0"),"/");
//        assignment.addSubmission(submission);
//        this.assignmentService.saveAssignment(assignment);
//        this.submissionService.saveSubmission(submission);
//
//        Assignment assignment=this.assignmentService.getAssignment(2);
//        System.out.println(assignment.getSubmissions());

        return "index";
    }

}

