package com.project.admin.controller;

import com.project.admin.form.StudentForm;
import com.project.admin.form.UpdateStudentForm;
import com.project.entity.Student;
import com.project.entity.User;
import com.project.entity.UserType;
import com.project.service.CourseService;
import com.project.service.StudentService;
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
@RequestMapping("admin/users/students")
public class StudentController {
    final private CourseService courseService;
    final private StudentService studentService;


    public StudentController(
            CourseService courseService,
            StudentService studentService
    ) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping
    public String admins(Model model) {
        model.addAttribute("students", studentService.getStudentList());
        return "admin/students/list-students";
    }

    @PostMapping(path = "/add")
    public String saveStudent(@Valid @ModelAttribute("studentForm") StudentForm form, BindingResult result, Model model, RedirectAttributes attrs) {
        form.setCourseOptions(courseService.getCourseOptions());
        if (result.hasErrors()) {
            return "admin/students/student-form";
        }
        if(!form.getPassword().equals(form.getConfirmPassword()))
        {
            ObjectError error = new ObjectError("password_mismatch", "Password Mismatch..");
            result.addError(error);
            return "admin/students/student-form";
        }
        try {
            User user = new User(
                    form.getUsername(),
                    form.getPassword(),
                    UserType.ROLE_STUDENT,
                    form.getStatus()
            );
            Student student = new Student(
                    form.getStudentFullName(),
                    form.getDateOfBirth(),
                    form.getContactNumber(),
                    form.getAddress()
            );
            student.setUser(user);
            student.getCourses().addAll(
                    courseService.getAllCourses(form.getCourses())
            );
            studentService.saveStudent(student);
        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "Username Already Exists..");
            result.addError(error);
            return "admin/students/student-form";
        }
        attrs.addFlashAttribute("success_message", "Student Added Successfully..");
        return "redirect:/admin/users/students";
    }

    @GetMapping(path = "/add")
    public String studentForm(Model model) {
        StudentForm studentForm = new StudentForm(courseService.getCourseOptions());
        model.addAttribute("studentForm", studentForm);
        return "admin/students/student-form";
    }

    @GetMapping(path = "/update/{id}/")
    public String updateStudentForm(@PathVariable("id") int id, Model model) {
        Student student = studentService.getStudent(id);
        UpdateStudentForm studentForm = new UpdateStudentForm(courseService.getCourseOptions());
        studentForm.setStudentData(student);
        model.addAttribute("studentForm", studentForm);
        model.addAttribute("updatemode", true);
        model.addAttribute("id", String.valueOf(id));
        return "admin/students/student-form";
    }

    @PostMapping("/update/{id}/")
    public String updateStudent(@Valid @ModelAttribute("studentForm") UpdateStudentForm form, BindingResult result, @PathVariable("id") int id, Model model, RedirectAttributes attrs) {
        form.setCourseOptions(courseService.getCourseOptions());
        if (result.hasErrors()) {
            model.addAttribute("updatemode", true);
            return "admin/students/student-form";
        }
        try {
            Student student = studentService.getStudent(id);
            User user = student.getUser();
            user.setUsername(form.getUsername());
            user.setUserStatus(form.getStatus());
            student.setStudentFullName(form.getStudentFullName());
            student.setDateOfBirth(form.getDateOfBirth());
            student.setContactNumber(form.getContactNumber());
            student.setAddress(form.getAddress());
            student.setCourses(courseService.getAllCourses(form.getCourses()));
            studentService.saveStudent(student);
        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "Admin User Already Exists..");
            result.addError(error);
            model.addAttribute("updatemode", true);
            return "admin/admins/admin-form";
        }
        attrs.addFlashAttribute("success_message", "Admin User Updated Successfully..");
        return "redirect:/admin/users/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, RedirectAttributes attrs) {
        studentService.deleteStudentById(id);
        attrs.addFlashAttribute("success_message", "Admin User Deleted Successfully...");
        return "redirect:/admin/users/students";
    }

}
