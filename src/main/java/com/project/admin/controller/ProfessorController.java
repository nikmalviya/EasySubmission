package com.project.admin.controller;

import com.project.admin.form.ProfessorForm;
import com.project.admin.form.UpdateProfessorForm;
import com.project.entity.Professor;
import com.project.entity.User;
import com.project.entity.UserType;
import com.project.service.ProfessorService;
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

@Controller
@RequestMapping("admin/users/professors")
public class ProfessorController {
    final private ProfessorService professorService;


    public ProfessorController(
            ProfessorService professorService
    ) {
        this.professorService = professorService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping
    public String professors(Model model) {
        model.addAttribute("professors", professorService.getProfessorList());
        return "admin/professors/list-professors";
    }

    @PostMapping(path = "/add")
    public String saveProfessor(@Valid @ModelAttribute("professorForm") ProfessorForm form, BindingResult result, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            return "admin/professors/professor-form";
        }
        if(!form.getPassword().equals(form.getConfirmPassword()))
        {
            ObjectError error = new ObjectError("password_mismatch", "Password Mismatch..");
            result.addError(error);
            return "admin/professors/professor-form";
        }
        try {
            User user = new User(
                    form.getUsername(),
                    form.getPassword(),
                    UserType.ROLE_PROFESSOR,
                    form.getStatus()
            );
            Professor professor = new Professor(
                    form.getProfessorFullName(),
                    form.getDateOfBirth(),
                    form.getContactNumber(),
                    form.getAddress(),
                    user
            );
            professorService.saveProfessor(professor);
        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "Username Already Exists..");
            result.addError(error);
            return "admin/professors/professor-form";
        }
        attrs.addFlashAttribute("success_message", "Professor Added Successfully..");
        return "redirect:/admin/users/professors";
    }

    @GetMapping(path = "/add")
    public String professorForm(Model model) {
        ProfessorForm professorForm = new ProfessorForm();
        model.addAttribute("professorForm", professorForm);
        return "admin/professors/professor-form";
    }

    @GetMapping(path = "/update/{id}/")
    public String updateProfessorForm(@PathVariable("id") int id, Model model) {
        Professor professor = professorService.getProfessor(id);
        UpdateProfessorForm professorForm = new UpdateProfessorForm();
        professorForm.setProfessorData(professor);
        model.addAttribute("professorForm", professorForm);
        model.addAttribute("updatemode", true);
        model.addAttribute("id", String.valueOf(id));
        return "admin/professors/professor-form";
    }

    @PostMapping("/update/{id}/")
    public String updateProfessor(@Valid @ModelAttribute("professorForm") UpdateProfessorForm form, BindingResult result, @PathVariable("id") int id, Model model, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            model.addAttribute("updatemode", true);
            return "admin/professors/professor-form";
        }
        try {
            Professor professor = professorService.getProfessor(id);
            User user = professor.getUser();
            user.setUsername(form.getUsername());
            user.setUserStatus(form.getStatus());
            professor.setProfessorFullName(form.getProfessorFullName());
            professor.setDateofBirth(form.getDateOfBirth());
            professor.setContactNumber(form.getContactNumber());
            professor.setAddress(form.getAddress());
            professorService.saveProfessor(professor);
        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "Admin User Already Exists..");
            result.addError(error);
            model.addAttribute("updatemode", true);
            return "admin/professors/professor-form";
        }
        attrs.addFlashAttribute("success_message", "Professor Updated Successfully..");
        return "redirect:/admin/users/professors";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, RedirectAttributes attrs) {
        try{
            professorService.deleteProfessorById(id);
            attrs.addFlashAttribute("success_message", "Professor Deleted Successfully...");
        } catch (DataIntegrityViolationException e){
            attrs.addFlashAttribute("error_message","Cannot Delete Professor.. Professor Should not be Assigned to Any Subject In order to Delete.");
        }
        return "redirect:/admin/users/professors";
    }

}
