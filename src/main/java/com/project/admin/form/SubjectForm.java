package com.project.admin.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashMap;

public class SubjectForm {
    @NotNull(message = "Required")
    @Size(min = 1, message = "Required")
    private String subjectTitle;
    private Integer courseId;
    private Integer professorId;
    private LinkedHashMap<Integer, String> courseOptions;
    private LinkedHashMap<Integer, String> professorOptions;

    public SubjectForm() {

    }

    public SubjectForm(LinkedHashMap<Integer, String> courseOptions, LinkedHashMap<Integer, String> professorOptions) {
        this.courseOptions = courseOptions;
        this.professorOptions = professorOptions;

    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
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
}
