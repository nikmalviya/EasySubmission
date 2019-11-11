package com.project.student.controller;

import com.project.entity.Subject;
import com.project.service.SubjectService;
import com.project.service.SubmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student/assignments")
public class AssignmentController {
    private final SubjectService subjectService;
    private final SubmissionService submissionService;

    public AssignmentController(SubjectService subjectService, SubmissionService submissionService) {
        this.subjectService = subjectService;
        this.submissionService = submissionService;
    }

    @GetMapping("/{subjectId}/")
    public String listAssignments(@PathVariable("subjectId") Integer subjectId, Model model) {
        Subject subject = subjectService.getSubject(subjectId);
        System.out.println(subject.getAssignments());
        model.addAttribute("subject", subject);
        model.addAttribute("submissionService",submissionService);
        return "student/list-assignments";
    }

    @GetMapping("/{subjectId}/{assignmentId}")
    public String assignmentDetail(@PathVariable("subjectId") int subjectId,
                                   @PathVariable("assignmentId") int assignmentId,
                                   Model model
    ) {
        model.addAttribute("subject", subjectService.getSubject(subjectId));
        return "student/assignment-detail";
    }
}
