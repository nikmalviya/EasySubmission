package com.project.entity;

import javax.persistence.*;
import java.util.Date;
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
    @ManyToOne
    //remaining
    private Subject subject;
    @Column(name = "status")
    private String status;
    @Column(name = "notes")
    private String notes;
    @Column(name = "file_path")
    private String filePath;

    public Assignment(){
    }

    public Assignment(String assignmentTitle, Date postedDate, Date deadlineDate, Subject subject, String status, String notes, String filePath) {
        this.assignmentTitle = assignmentTitle;
        this.postedDate = postedDate;
        this.deadlineDate = deadlineDate;
        this.subject = subject;
        this.status = status;
        this.notes = notes;
        this.filePath = filePath;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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