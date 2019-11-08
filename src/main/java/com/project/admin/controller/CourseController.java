package com.project.admin.controller;

import com.project.admin.form.CourseForm;
import com.project.entity.Course;
import com.project.service.CourseService;
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
@RequestMapping("admin/courses")
public class CourseController {
    final private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getCourseList());
        return "admin/courses/list-courses";
    }

    @PostMapping(path = "/add")
    public String saveCourse(@Valid @ModelAttribute("course") CourseForm form, BindingResult result, Model model, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            return "admin/courses/course-form";
        }
        try {
            courseService.saveCourse(new Course(form.getCourseTitle()));
        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "Course Already Exists..");
            result.addError(error);
            return "admin/courses/course-form";
        }
        attrs.addFlashAttribute("success_message", "Course Added Successfully..");
        return "redirect:/admin/courses";
    }

    @GetMapping(path = "/add")
    public String addCourseForm(Model model) {
        CourseForm course = new CourseForm();
        model.addAttribute("course", course);
        return "admin/courses/course-form";
    }

    @GetMapping(path = "/update/{id}/")
    public String updateCourseForm(@PathVariable("id") int id, Model model) {
//        System.out.println(courseId);
        Course course = courseService.getCourse(id);
        CourseForm courseform = new CourseForm();
        courseform.setCourseTitle(course.getCourseTitle());
        model.addAttribute("course", courseform);
        model.addAttribute("updatemode", true);
        model.addAttribute("id", String.valueOf(id));
        return "admin/courses/course-form";
    }

    @PostMapping("/update/{id}/")
    public String updateCourse(@Valid @ModelAttribute("course") CourseForm form, BindingResult result, @PathVariable("id") int id, Model model, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            return "admin/courses/course-form";
        }
        try {
            Course course = courseService.getCourse(id);
            course.setCourseTitle(form.getCourseTitle());
            courseService.saveCourse(course);
        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "Course Already Exists..");
            result.addError(error);
            model.addAttribute("updatemode", true);
            return "admin/courses/course-form";
        }
        attrs.addFlashAttribute("success_message", "Course Updated Successfully..");
        return "redirect:/admin/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id, RedirectAttributes attrs) {
        courseService.deleteCourseById(id);
        attrs.addFlashAttribute("success_message", "Course Deleted Successfully...");
        return "redirect:/admin/courses";
    }

}
