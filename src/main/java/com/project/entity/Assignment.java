package com.project.entity;

import com.project.professor.form.AssignmentForm;

import javax.persistence.*;
import java.io.File;
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

    @Column(name = "posted_date")
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
    private String status;
    @Column(name = "notes")
    private String notes;
    @Column(name = "file_path")
    private String filePath;
    @OneToMany(mappedBy = "assignment", cascade = {CascadeType.ALL})
    private List<Question> questions;
    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
    private List<Submission> submissions;

    public Assignment() {
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

    public Assignment(AssignmentForm assignmentForm) {
        this.assignmentTitle = assignmentForm.getAssignmentTitle();
        this.postedDate = new Date();
        this.deadlineDate = assignmentForm.getDeadlinedate();
        this.subject = assignmentForm.getSubject();
        this.status = assignmentForm.getAssignmentStatus();
        this.notes = assignmentForm.getNotes();
        this.filePath = assignmentForm.getFilePath();
    }


    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
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

    public String getFileName() {
        String file = new File(filePath).getName();
        return file.substring(file.indexOf("-") + 1);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentID=" + assignmentID +
                ", assignmentTitle='" + assignmentTitle + '\'' +
                ", postedDate=" + postedDate +
                ", deadlineDate=" + deadlineDate +
                ", subject=" + subject +
                ", status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}