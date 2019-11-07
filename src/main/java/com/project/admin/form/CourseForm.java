package com.project.admin.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CourseForm {


    @NotNull(message = "Required")
    @Size(min = 1, message = "Required")
    private String courseTitle;

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
