package com.project.student.controller;

import com.project.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student/assignments")
public class AssignmentController {
    private final SubjectService subjectService;

    public AssignmentController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{subjectId}/")
    public String listAssignments(@PathVariable("subjectId") Integer subjectId, Model model) {
        model.addAttribute("subject", subjectService.getSubject(subjectId));
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
