package com.project.entity;

import java.util.Date;

public class Assignment {

    private int assignmentID;
    private String assignmentTitle;
    private Date postedDate;
    private Date deadlineDate;
    private Subject subject;
    private String status;
    private String notes;
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
