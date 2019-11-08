package com.project.admin.form;

import com.project.entity.Course;
import com.project.entity.Professor;
import com.project.service.CourseService;
import com.project.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashMap;

public class SubjectForm {
    @NotNull(message = "Required")
    @Size(min = 1, message = "Required")
    private String subjectTitle;
    private Integer courseId;
    private Integer professorId;

    private ProfessorService professorService;

    private CourseService courseService;

    private LinkedHashMap<Integer, String> courseOptions;
    private LinkedHashMap<Integer, String> professorOptions;

    private Course course;
    private Professor professor;

    public SubjectForm() {

    }

    public SubjectForm(CourseService courseService, ProfessorService professorService) {
        this.courseService = courseService;
        this.professorService=professorService;
        courseOptions=this.courseService.getCourseOptions();
        professorOptions=this.professorService.getProfessorOptions();

    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public LinkedHashMap<Integer, String> getCourseOptions() {
        return courseOptions;
    }

    public void setCourseOptions(LinkedHashMap<Integer, String> courseOptions) {
        this.courseOptions = courseOptions;
    }

    public LinkedHashMap<Integer, String> getProfessorOptions() {
        return professorOptions;
    }

    public void setProfessorOptions(LinkedHashMap<Integer, String> professorOptions) {
        this.professorOptions = professorOptions;
    }

    public CourseService getCourseService() {
        return courseService;
    }


    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }


    public void setCourse(Course course) {
        this.course = course;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public ProfessorService getProfessorService() {
        return professorService;
    }


}
