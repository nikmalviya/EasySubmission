package com.project.entity;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFRow;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentID;

    @Column(name = "student_name")
    private String studentFullName;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "address")
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    public Student() {
    }

    public Student(Cell cell, Iterator<Cell> cellIterator) throws ParseException {
        cell = cellIterator.next();
        this.setStudentFullName(cell.getStringCellValue());
        cell = cellIterator.next();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = cell.getStringCellValue();
        Date dob = dateFormat.parse(date);
        this.setDateOfBirth(dob);
        cell = cellIterator.next();
        Double val=cell.getNumericCellValue();
        this.setContactNumber(String.format("%.0f",val));
        cell = cellIterator.next();
        this.setAddress(cell.getStringCellValue());
        this.setUser(user);
    }
    public Student(String studentFullName, Date dateOfBirth, String contactNumber, String address) {
        this.studentFullName = studentFullName;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", studentFullName='" + studentFullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", contactNumber='" + contactNumber + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                ", courses=" + courses +
                '}';
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
