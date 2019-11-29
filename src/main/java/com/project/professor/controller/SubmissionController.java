package com.project.professor.controller;

import com.project.entity.Assignment;
import com.project.entity.Subject;
import com.project.entity.Submission;
import com.project.entity.SubmissionStatus;
import com.project.professor.form.SubmissionForm;
import com.project.service.AssignmentService;
import com.project.service.SubjectService;
import com.project.service.SubmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @RequestMapping(path = "{assignmentId}")
    public String listSubmission(Model model, @PathVariable("assignmentId") int assignmentId) {
        Assignment assignment = this.assignmentService.getAssignment(assignmentId);
        List<Submission> submissions = assignment.getSubmissions();
        System.out.println(submissions);
        model.addAttribute("assignmentId",assignmentId);
        model.addAttribute("subject",assignment.getSubject());
        model.addAttribute("submissions", submissions);
        return "/professor/list-submissions";
    }

    @GetMapping(path ="{assignmentId}/{submissionID}")
    public String getEvaluateSubmission(Model model, @PathVariable("assignmentId") int assignementId, @PathVariable("submissionID") int submissionID) {
        Submission submission=this.submissionService.getSubmission(submissionID);
        Assignment assignment=submission.getAssignment();
        Subject subject=submission.getSubject();
        SubmissionForm submissionForm=new SubmissionForm();
        model.addAttribute("assignment",assignment);
        model.addAttribute("submissionForm",submissionForm);
        model.addAttribute("student",submission.getStudent());
        model.addAttribute("submission",submission);
        model.addAttribute("subject",subject);
        model.addAttribute("submissionService", submissionService);
        return "/professor/submission-detail";
    }

    @PostMapping(path="{assignmentId}/{submissionID}")
    public String evaluateSubmission(@Valid @ModelAttribute("submissionForm") SubmissionForm submissionForm, Model model, @PathVariable("assignmentId") int assignementId, @PathVariable("submissionID") int submissionID)
    {

        Submission submission=this.submissionService.getSubmission(submissionID);
        submission.setMarks(submissionForm.getMarks());
        submission.setStatus(SubmissionStatus.GRADED);
        Assignment assignment=assignmentService.getAssignment(assignementId);
        submission.setAssignment(assignment);
        submissionService.saveSubmission(submission);
        System.out.println("makrs="+submissionForm.getMarks());
        return "redirect:/professors/submissions/"+assignementId+"/";

    }
}
