package com.project.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name ="professors")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private int professorID;
    @Column(name = "professor_name")
    private String professorFullName;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateofBirth;
    @Column(name = "contact_number")
    private String contactNumber;
    @Column(name = "address")
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
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