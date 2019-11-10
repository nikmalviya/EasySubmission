package com.project.professor.controller;

import com.project.entity.Assignment;
import com.project.entity.Subject;
import com.project.professor.form.AssignmentForm;
import com.project.service.AssignmentService;
import com.project.service.ProfessorService;
import com.project.service.SubjectService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


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



    @GetMapping(path="{id}/assignments")
    public String assignments(Model model,@PathVariable("id") int id)
    {
        List<Assignment> assignments=this.subjectService.getSubject(id).getAssignments();
        System.out.println(assignments);
        model.addAttribute("assignments",assignments);
        return "index";//change it
    }


    @GetMapping(path = "{id}/assignments/add")
    public String AssignmentForm(Model model,@PathVariable("id") int id) {
        AssignmentForm assignmentForm = new AssignmentForm();
        model.addAttribute("assignmentForm", assignmentForm);
        Subject subject=this.subjectService.getSubject(id);
        model.addAttribute("subjectName",subject.getSubjectName());
        return "/professor/assignment-form";
    }

    @PostMapping(path = "{id}/assignments/add")
    public String saveAssignment(@Valid @ModelAttribute("assignmentForm") AssignmentForm assignmentForm, BindingResult result, Model model, RedirectAttributes attrs,@PathVariable("id") int id) throws IOException {
        Subject subject=this.subjectService.getSubject(id);
        if (result.hasErrors()) {
            model.addAttribute("updatemode", true);
            model.addAttribute("assignmentForm",new AssignmentForm());
            return "/professor/assignment-form";
        }
        try {
            MultipartFile multiPartFile=assignmentForm.getFile();
            String url="/uploads/assignment"+multiPartFile.getName();
            File file=new File(url);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] bytes = multiPartFile.getBytes();
            fos.write(bytes);
            fos.close();
            System.out.println("file is been uploaded"+multiPartFile.getOriginalFilename());
            model.addAttribute("message", String.format("File %s with size %d bytes has been uploaded succesfully"
                    , multiPartFile.getOriginalFilename(), multiPartFile.getSize()));
            assignmentForm.setFilePath(url);
            assignmentForm.setSubject(subject);
            Assignment assignment=new Assignment(assignmentForm);
            this.assignmentService.saveAssignment(assignment);
        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "File Already Exists..");
            result.addError(error);
            return "/professor/assignment-form";
        }
        attrs.addFlashAttribute("success_message", "Subject Added Successfully..");
        return "redirect:/professors/"+id;
    }

    @GetMapping(path = "{subjectid}/update/{id}/")
    public String updateSubjectForm(@PathVariable("subjectid") int subjectId,@PathVariable("id") int id, Model model) {
        Subject subject = subjectService.getSubject(subjectId);
        AssignmentForm assignmentForm=new AssignmentForm(this.assignmentService.getAssignment(id));
        model.addAttribute("assignmentForm", assignmentForm);
        model.addAttribute("updatemode", true);
        model.addAttribute("id", String.valueOf(subjectId));
        model.addAttribute("subject",subject.getSubjectName());
        return "/professor/assignment-form";
    }


    @PostMapping("/{subjectid}/update/{id}/")
    public String updateAssignment(@Valid @ModelAttribute("assignmentForm") AssignmentForm assignmentForm, BindingResult result,@PathVariable("subjectId") int subjectid, @PathVariable("id") int id, Model model, RedirectAttributes attrs) throws FileNotFoundException {
        Subject subject=this.subjectService.getSubject(subjectid);
        if (result.hasErrors()) {
            model.addAttribute("updatemode", true);
            model.addAttribute("assignmentForm",new AssignmentForm());
            return "/professor/assignment-form";
        }
        try {
            MultipartFile multiPartFile=assignmentForm.getFile();
            String url="/uploads/assignment"+multiPartFile.getName();
            File file=new File(url);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] bytes = multiPartFile.getBytes();
            fos.write(bytes);
            fos.close();
            System.out.println("file is been uploaded"+multiPartFile.getOriginalFilename());
            model.addAttribute("message", String.format("File %s with size %d bytes has been uploaded succesfully"
                    , multiPartFile.getOriginalFilename(), multiPartFile.getSize()));

            Assignment assignment=new Assignment(assignmentForm);
            assignment.setAssignmentID(id);
            assignment.setSubject(subject);//no need
            this.assignmentService.saveAssignment(assignment);
        } catch (DataIntegrityViolationException | IOException e) {
            ObjectError error = new ObjectError("already_exist_error", "Subject Already Exists..");
            result.addError(error);
            model.addAttribute("updatemode", true);
            return "admin/subjects/subject-form";
        }
        attrs.addFlashAttribute("success_message", "Subject Updated Successfully..");
        return "redirect:/admin/subjects";
    }

}
