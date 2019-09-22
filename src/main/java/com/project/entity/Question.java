package com.project.entity;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int questionID;
    @Column(name = "question_text")
    private String questionText;
    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;
    public Question(String questionText, Assignment assignment) {
        this.questionText = questionText;
        this.assignment = assignment;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
