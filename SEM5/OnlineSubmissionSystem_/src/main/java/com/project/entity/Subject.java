package com.project.entity;

public class Subject {

    private int subjectID;
    private String subjectName;
    private Course course;
    private Professor professor;

    public Subject(){
    }

    public Subject(String subjectName, Course course, Professor professor) {
        this.subjectName = subjectName;
        this.course = course;
        this.professor = professor;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
