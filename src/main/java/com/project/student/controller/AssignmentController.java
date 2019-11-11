package com.project.student.controller;

import com.project.entity.Student;
import com.project.entity.Subject;
import com.project.service.AssignmentService;
import com.project.service.SubjectService;
import com.project.service.SubmissionService;
import com.project.student.form.SubmissionForm;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/student/assignments")
public class AssignmentController {
    private final SubjectService subjectService;
    private final SubmissionService submissionService;
    private final AssignmentService assignmentService;

    public AssignmentController(SubjectService subjectService,
                                SubmissionService submissionService,
                                AssignmentService assignmentService
    ) {
        this.subjectService = subjectService;
        this.submissionService = submissionService;
        this.assignmentService = assignmentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
        // dataBinder.setValidator(new AssignmentValidator());
    }
    @GetMapping("/{subjectId}/")
    public String listAssignments(@PathVariable("subjectId") Integer subjectId, Model model) {
        Subject subject = subjectService.getSubject(subjectId);
        subject.getAssignments().removeIf(a -> a.getStatus().equals("INACTIVE"));
        model.addAttribute("subject", subject);
        model.addAttribute("submissionService", submissionService);
        return "student/list-assignments";
    }

    @GetMapping("/{subjectId}/{assignmentId}")
    public String assignmentDetail(@PathVariable("subjectId") int subjectId,
                                   @PathVariable("assignmentId") int assignmentId,
                                   Model model
    ) {
        model.addAttribute("subject", subjectService.getSubject(subjectId));
        model.addAttribute("submissionService", submissionService);
        model.addAttribute("assignment", assignmentService.getAssignment(assignmentId));
        SubmissionForm form = new SubmissionForm();
        model.addAttribute("submissionForm",form);
        return "student/assignment-detail";
    }

    @PostMapping("/{subjectId}/{assignmentId}")
    public String submitAssignment(@Valid @ModelAttribute("submissionForm") SubmissionForm submissionForm,
                                   BindingResult result,
                                   @PathVariable("subjectId") int subjectId,
                                   @PathVariable("assignmentId") int assignmentId,
                                   @SessionAttribute("student") Student student,
                                   Model model) throws IOException {
        model.addAttribute("subject", subjectService.getSubject(subjectId));
        model.addAttribute("submissionService", submissionService);
        model.addAttribute("assignment", assignmentService.getAssignment(assignmentId));
        if (result.hasErrors()) {
            System.out.println("Hello world");
            return "student/assignment-detail";
        }
        submissionService.submitAssignment(submissionForm,
                subjectService.getSubject(subjectId),
                assignmentService.getAssignment(assignmentId),
                student);

        return "student/assignment-detail";
    }
}
