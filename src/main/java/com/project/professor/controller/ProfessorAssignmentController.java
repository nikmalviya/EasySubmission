package com.project.professor.controller;

import com.project.entity.Assignment;
import com.project.entity.Subject;
import com.project.professor.form.AssignmentForm;
import com.project.service.AssignmentService;
import com.project.service.ProfessorService;
import com.project.service.SubjectService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/professors")
public class ProfessorAssignmentController {
    final private ProfessorService professorService;
    final private AssignmentService assignmentService;
    final private SubjectService subjectService;

    public ProfessorAssignmentController(ProfessorService professorService, AssignmentService assignmentService, SubjectService subjectService1) {
        this.professorService = professorService;
        this.assignmentService = assignmentService;
        this.subjectService = subjectService1;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
        // dataBinder.setValidator(new AssignmentValidator());
    }


    @GetMapping(path = "{id}/assignments")
    public String assignments(Model model, @PathVariable("id") int id) {

        Subject subject = this.subjectService.getSubject(id);
        List<Assignment> assignments = subject.getAssignments();
        model.addAttribute("assignmentForm", new AssignmentForm());
        model.addAttribute("assignments", assignments);
        model.addAttribute("subject", subject);

        return "/professor/list-assignments";
    }


    @GetMapping(path = "{id}/assignments/add")
    public String AssignmentForm(Model model, @PathVariable("id") int id) {
        AssignmentForm assignmentForm = new AssignmentForm();
        model.addAttribute("assignmentForm", assignmentForm);
        Subject subject = this.subjectService.getSubject(id);
        model.addAttribute("subjectName", subject.getSubjectName());
        model.addAttribute("subjectId", id);
        model.addAttribute("subject", subject);
        return "/professor/assignment-form";
    }

    @PostMapping(path = "{id}/assignments/add")
    public String saveAssignment(@Valid @ModelAttribute("assignmentForm") AssignmentForm assignmentForm, BindingResult result, Model model, RedirectAttributes attrs, @PathVariable("id") int id) throws IOException {
        Subject subject = this.subjectService.getSubject(id);
        if (result.hasErrors()) {
            return "/professor/assignment-form";
        }
        try {
            this.assignmentService.saveAssignment(assignmentForm, subject);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e);
            ObjectError error = new ObjectError("already_exist_error", "Assignment Already Exists..");
            result.addError(error);
            return "/professor/assignment-form";
        } catch (Exception e) {
            System.out.println(e);
        }
        attrs.addFlashAttribute("success_message", "Subject Added Successfully..");
        return "redirect:/professors/" + id + "/assignments";
    }

    @GetMapping(path = "{subjectid}/assignments/update/{id}")
    public String updateSubjectForm(Model model, @PathVariable("subjectid") int subjectId, @PathVariable("id") int id) throws IOException {
        Subject subject = subjectService.getSubject(subjectId);
        Assignment assignment = this.assignmentService.getAssignment(id);
        AssignmentForm assignmentForm = new AssignmentForm(this.assignmentService.getAssignment(id));
//
//        File file=new File(assignment.getFilePath());
//        FileInputStream input=new FileInputStream(file);
//        MultipartFile multipartFile=new MockMultipartFile(file.getName(),input);
//        assignmentForm.setFile(multipartFile);

        model.addAttribute("assignmentForm", assignmentForm);
        model.addAttribute("updatemode", true);
        model.addAttribute("id", String.valueOf(subjectId));
        model.addAttribute("subjectTitle", subject.getSubjectName());
        return "/professor/assignment-form";
    }


    @PostMapping("{subjectid}/assignments/update/{id}")
    public String updateAssignment(@Valid @ModelAttribute("assignmentForm") AssignmentForm assignmentForm, BindingResult result, @PathVariable("subjectid") int subjectid, @PathVariable("id") int id, Model model, RedirectAttributes attrs) throws FileNotFoundException {
        Subject subject = this.subjectService.getSubject(subjectid);
        Assignment assignment = this.assignmentService.getAssignment(id);
        if (result.hasErrors()) {
            model.addAttribute("updatemode", true);
            return "/professor/assignment-form";
        }
        try {

            this.assignmentService.updateAssignment(assignment, assignmentForm, subject);

        } catch (DataIntegrityViolationException | IOException e) {
            System.out.println(e);
            ObjectError error = new ObjectError("already_exist_error", "Subject Already Exists..");
            result.addError(error);
            model.addAttribute("updatemode", true);
            return "/professor/assignment-form";
        }
        attrs.addFlashAttribute("success_message", "Subject Updated Successfully..");
        return "redirect:/professors/" + subjectid + "/assignments";
    }

    @GetMapping("{subjectid}/assignments/delete/{id}")
    public String deleteSubject(@PathVariable("subjectid") int subjectId, @PathVariable("id") int id, RedirectAttributes attrs) throws IOException {
        Assignment assignment = this.assignmentService.getAssignment(id);
        try{
            Files.delete(Paths.get(assignment.getFilePath()));
        } catch (NoSuchFileException ignored){}
        this.assignmentService.deleteAssignment(assignment);
        attrs.addFlashAttribute("success_message", "Subject Deleted Successfully...");
        return "redirect:/professors/" + subjectId + "/assignments";
    }
}
