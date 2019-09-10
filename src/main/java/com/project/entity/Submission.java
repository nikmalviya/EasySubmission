package com.project.entity;

public class Submission {
    private int submissionID;
    private Assignment assignment;
    private Subject subject;
    private Student student;
    private String status;
    private Float marks;
    private String filePath;

    public Submission(){
    }

    public Submission(Assignment assignment, Subject subject, Student student, String status, Float marks, String filePath) {
        this.assignment = assignment;
        this.subject = subject;
        this.student = student;
        this.status = status;
        this.marks = marks;
        this.filePath = filePath;
    }

    public int getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(int submissionID) {
        this.submissionID = submissionID;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getMarks() {
        return marks;
    }

    public void setMarks(Float marks) {
        this.marks = marks;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
