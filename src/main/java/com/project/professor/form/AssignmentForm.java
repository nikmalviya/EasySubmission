package com.project.professor.form;

import com.project.entity.Assignment;
import com.project.entity.Subject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class AssignmentForm {
    @NotBlank(message = "Required")
    private String assignmentTitle;
    @NotNull(message = "Required")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date postdate;
    @NotNull(message = "Required")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date deadlinedate;
    @NotBlank(message = "Required")
    private String assignmentStatus;
    @NotBlank(message = "Required")
    private String notes;
    private MultipartFile file;

    private Subject subject;
    private String filePath;

    public AssignmentForm() {
    }

    public AssignmentForm(Assignment assignment) {
        this.assignmentTitle=assignment.getAssignmentTitle();
        this.postdate=assignment.getPostedDate();
        this.deadlinedate=assignment.getDeadlineDate();
        this.assignmentStatus=(true==assignment.getStatus())?"ACTIVE":"INACTIVE";
        this.notes=assignment.getNotes();
        this.subject=assignment.getSubject();
    }


    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    public Date getDeadlinedate() {
        return deadlinedate;
    }

    public void setDeadlinedate(Date deadlinedate) {
        this.deadlinedate = deadlinedate;
    }

    public String getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(String assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
