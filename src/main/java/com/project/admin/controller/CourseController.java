package com.project.admin.controller;

import com.project.entity.Course;
import com.project.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/courses")
public class CourseController {
    final private CourseService courseService;

    CourseController(CourseService courseService) {

        this.courseService = courseService;
    }

    @GetMapping
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getCourseList());
        return "courses";
    }

    @PostMapping
    public String saveCourse(Model model, HttpServletRequest request) {
        courseService.saveCourse(new Course(request.getParameter("title")));
        model.addAttribute("courses", courseService.getCourseList());
        return "courses";
    }

}
