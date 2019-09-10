package com.project.entity;

import java.util.Date;

public class Professor {
    private int professorID;
    private String professorFullName;
    private Date dateofBirth;
    private String contactNumber;
    private String address;
    private User user;

    public Professor(){
    }

    public Professor(String professorFullName, Date dateofBirth, String contactNumber, String address, User user) {
        this.professorFullName = professorFullName;
        this.dateofBirth = dateofBirth;
        this.contactNumber = contactNumber;
        this.address = address;
        this.user = user;
    }

    public int getProfessorID() {
        return professorID;
    }

    public void setProfessorID(int professorID) {
        this.professorID = professorID;
    }

    public String getProfessorFullName() {
        return professorFullName;
    }

    public void setProfessorFullName(String professorFullName) {
        this.professorFullName = professorFullName;
    }

    public Date getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(Date dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
