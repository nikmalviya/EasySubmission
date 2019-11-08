package com.project.admin.controller;

import com.project.admin.form.AdminForm;
import com.project.entity.User;
import com.project.entity.UserType;
import com.project.service.CourseService;
import com.project.service.UserService;
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
@RequestMapping("admin/users/admins")
public class AdminController {
    final private CourseService courseService;
    final private UserService userService;

    public AdminController(
            CourseService courseService,
            UserService userService
    ) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping
    public String admins(Model model) {
        model.addAttribute("admins", userService.getUserListByType(UserType.ADMIN));
        return "admin/admins/list-admins";
    }

    @PostMapping(path = "/add")
    public String saveAdmin(@Valid @ModelAttribute("adminForm") AdminForm form, BindingResult result, Model model, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            return "admin/admins/admin-form";
        }
        if(!form.getPassword().equals(form.getConfirmPassword()))
        {
            ObjectError error = new ObjectError("password_mismatch", "Password Mismatch..");
            result.addError(error);
            return "admin/admins/admin-form";
        }
        try {
            userService.saveUser(new User(
                    form.getUsername(),
                    form.getPassword(),
                    UserType.ADMIN,
                    form.getStatus()
            ));
        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "Admin User Already Exists..");
            result.addError(error);
            return "admin/admins/admin-form";
        }
        attrs.addFlashAttribute("success_message", "Admin User Added Successfully..");
        return "redirect:/admin/users/admins";
    }

    @GetMapping(path = "/add")
    public String adminForm(Model model) {
        AdminForm adminForm = new AdminForm();
        model.addAttribute("adminForm", adminForm);
        return "admin/admins/admin-form";
    }

    @GetMapping(path = "/update/{id}/")
    public String updateAdminForm(@PathVariable("id") int id, Model model) {
//        System.out.println(courseId);
        User user = userService.getUser(id);
        AdminForm adminForm = new AdminForm();
        adminForm.setUsername(user.getUsername());
        adminForm.setPassword(user.getPassword());
        adminForm.setConfirmPassword(user.getPassword());
        adminForm.setStatus(user.getUserStatus());
        model.addAttribute("adminForm", adminForm);
        model.addAttribute("updatemode", true);
        model.addAttribute("id", String.valueOf(id));
        return "admin/admins/admin-form";
    }

    @PostMapping("/update/{id}/")
    public String updateCourse(@Valid @ModelAttribute("adminForm") AdminForm form, BindingResult result, @PathVariable("id") int id, Model model, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            model.addAttribute("updatemode", true);
            return "admin/admins/admin-form";
        }
        if(!form.getPassword().equals(form.getConfirmPassword()))
        {
            ObjectError error = new ObjectError("password_mismatch", "Password Mismatch..");
            result.addError(error);
            model.addAttribute("updatemode", true);
            return "admin/admins/admin-form";
        }
        try {
            User user = userService.getUser(id);
            user.setUsername(form.getUsername());
            user.setPassword(form.getPassword());
            user.setUserType(UserType.ADMIN);
            user.setUserStatus(form.getStatus());
            userService.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            ObjectError error = new ObjectError("already_exist_error", "Admin User Already Exists..");
            result.addError(error);
            model.addAttribute("updatemode", true);
            return "admin/admins/admin-form";
        }
        attrs.addFlashAttribute("success_message", "Admin User Updated Successfully..");
        return "redirect:/admin/users/admins";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable("id") int id, RedirectAttributes attrs) {
        userService.deleteUserById(id);
        attrs.addFlashAttribute("success_message", "Admin User Deleted Successfully...");
        return "redirect:/admin/users/admins";
    }

}
