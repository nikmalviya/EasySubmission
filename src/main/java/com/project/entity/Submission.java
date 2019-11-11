package com.project.entity;

import javax.persistence.*;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private int submissionID;
    @ManyToOne(cascade = {
            CascadeType.MERGE
    })
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;
    @ManyToOne(cascade = {
            CascadeType.MERGE
    })
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToOne(cascade = {
            CascadeType.MERGE
    })
    @JoinColumn(name = "student_id")
    private Student student;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;
    @Column(name = "marks")
    private Float marks;
    @Column(name = "file_path")
    private String filePath;

    public Submission() {
    }

    public Submission(Assignment assignment, Subject subject, Student student, SubmissionStatus status, Float marks, String filePath) {
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

    public SubmissionStatus getStatus() {
        return status;
    }

    public void setStatus(SubmissionStatus status) {
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