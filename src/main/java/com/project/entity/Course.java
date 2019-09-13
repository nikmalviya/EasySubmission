package com.project.entity;

public class Course {
    private int courseID;
    private String courseTitle;

    public Course(){
    }

    public Course(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
