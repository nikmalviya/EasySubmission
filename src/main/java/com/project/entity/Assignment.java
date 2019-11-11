package com.project.entity;

import com.project.professor.form.AssignmentForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "assignments")
public class Assignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private int assignmentID;
    @Column(name = "assignment_title")
    private String assignmentTitle;

    @Column(name="posted_date")
    private Date postedDate;

    @Column(name = "deadline_date")
    private Date deadlineDate;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @Column(name = "status")
    private boolean status;
    @Column(name = "notes")
    private String notes;
    @Column(name = "file_path")
    private String filePath;
    @OneToMany(mappedBy = "assignment",cascade = {CascadeType.ALL})
    private List<Question> questions;
    @OneToMany(mappedBy = "assignment",cascade = CascadeType.ALL)
    private List<Submission> submissions;
    public Assignment(){
    }

    public Assignment(String assignmentTitle, Date postedDate, Date deadlineDate, Subject subject, boolean status, String notes, String filePath) {
        this.assignmentTitle = assignmentTitle;
        this.postedDate = postedDate;
        this.deadlineDate = deadlineDate;
        this.subject = subject;
        this.status = status;
        this.notes = notes;
        this.filePath = filePath;
    }

    public Assignment(AssignmentForm assignmentForm) {
        this.assignmentTitle=assignmentForm.getAssignmentTitle();
        this.postedDate=assignmentForm.getPostdate();
        this.deadlineDate=assignmentForm.getDeadlinedate();
        this.subject=assignmentForm.getSubject();
        this.status=("ACTIVE".equals(assignmentForm.getAssignmentStatus()))?true:false;
        this.notes=assignmentForm.getNotes();
        this.filePath=assignmentForm.getFilePath();
    }



    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}