package com.project.professor.controller;

import com.project.entity.Assignment;
import com.project.entity.Submission;
import com.project.service.AssignmentService;
import com.project.service.SubjectService;
import com.project.service.SubmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path="professors/submissions")
public class SubmissionController {

    final private SubmissionService submissionService;
    final private SubjectService subjectService;
    final private AssignmentService assignmentService;

    public SubmissionController(SubmissionService submissionService, SubjectService subjectService, AssignmentService assignmentService) {
        this.submissionService = submissionService;
        this.subjectService = subjectService;
        this.assignmentService = assignmentService;
    }
    @RequestMapping(path="{assignmentId}")
    public String listSubmission(Model model, @PathVariable("assignmentId") int assignmentId)
    {
        Assignment assignment=this.assignmentService.getAssignment(assignmentId);
        List<Submission> submissions =assignment.getSubmissions();
        System.out.println(submissions);
        model.addAttribute("submissions",submissions);
        return "/professor/list-submissions";
    }
}
