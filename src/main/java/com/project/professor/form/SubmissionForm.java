package com.project.professor.form;

import javax.validation.constraints.NotNull;

public class SubmissionForm {

    @NotNull
    //@NotBlank(message = "Required")
    private Float marks;

    public Float getMarks() {
        return marks;
    }

    public void setMarks(Float marks) {
        this.marks = marks;
    }
}
