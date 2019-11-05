package com.project.entity;

public class Question {
    private int questionID;
    private String questionText;
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
