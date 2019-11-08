package com.project.admin.controller;

import com.project.admin.form.SubjectForm;
import com.project.entity.Course;
import com.project.entity.Professor;
import com.project.entity.Subject;
import com.project.service.CourseService;
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

@Controller
@RequestMapping("admin/subjects")
public class SubjectController {
    final private SubjectService subjectService;
    final private CourseService courseService;
    final private ProfessorService professorService;

    SubjectController(SubjectService subjectService,CourseService courseService, ProfessorService professorService) {
        this.subjectService = subjectService;
        this.courseService=courseService;
        this.professorService=professorService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping
    public String subjects(Model model) {
        model.addAttribute("subject", subjectService.getSubjectList());
        return "admin/subjects/list-subjects";
    }

    @PostMapping(path = "/add")
    public String saveCourse(@Valid @ModelAttribute("subject") SubjectForm form, BindingResult result, Model model, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            return "admin/subjects/subject-form";
        }
        try {

            Course course=courseService.getCourse(form.getCourseId());
            Professor professor=professorService.getProfessor(form.getProfessorId());
            subjectService.saveSubject(new Subject(form.getSubjectTitle(),course,professor));
        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "Subject Already Exists..");
            result.addError(error);
            return "admin/subjects/subject-form";
        }
        attrs.addFlashAttribute("success_message","Subject Added Successfully..");
        return "redirect:/admin/subjects";
    }

    @GetMapping(path = "/add")
    public String addSubjectForm(Model model) {
        SubjectForm subject=new SubjectForm(courseService,professorService);
        model.addAttribute("subject", subject);
        return "admin/subjects/subject-form";
    }

    @GetMapping(path = "/update/{id}/")
    public String updateSubjectForm(@PathVariable("id") int id, Model model) {
//        System.out.println(courseId);
        Subject subject=subjectService.getSubject(id);
        SubjectForm subjectForm=new SubjectForm(courseService,professorService);
        subjectForm.setSubjectTitle(subject.getSubjectName());
        model.addAttribute("subject", subjectForm);
        model.addAttribute("updatemode", true);
        model.addAttribute("id", String.valueOf(id));
        return "admin/subjects/subject-form";
    }

    @PostMapping("/update/{id}/")
    public String updateSubject(@Valid @ModelAttribute("subject") SubjectForm form, BindingResult result, @PathVariable("id") int id, Model model, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            return "admin/subjects/subject-form";
        }
        try {
            Course course=courseService.getCourse(form.getCourseId());
            Professor professor=professorService.getProfessor(form.getProfessorId());
            Subject subject=subjectService.getSubject(id);
            subject.setSubjectName(form.getSubjectTitle());
            subject.setCourse(course);
            subject.setProfessor(professor);
            subjectService.saveSubject(subject);
        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "Subject Already Exists..");
            result.addError(error);
            model.addAttribute("updatemode", true);
            return "admin/subjects/subject-form";
        }
        attrs.addFlashAttribute("success_message","Subject Updated Successfully..");
        return "redirect:/admin/subjects";
    }

    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable("id") int id, RedirectAttributes attrs) {
        subjectService.deleteSubjectById(id);

        attrs.addFlashAttribute("success_message","Subject Deleted Successfully...");
        return "redirect:/admin/subjects";
    }

}
