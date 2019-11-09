package com.project.admin.form;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class StudentForm extends AdminForm {
    @NotBlank(message = "Required")
    private String studentFullName;
    @NotNull(message = "Required")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;
    @NotBlank(message = "Required")
    @Digits(integer = 10, fraction = 0, message = "Should Contain Only Numbers")
    @Length(min = 10, max = 10, message = "Contact Number should be of 10 Digits")
    private String contactNumber;
    @NotBlank(message = "Required")
    private String address;
    @NonNull
    @Size(min = 1, message = "Select at Least one Course")
    private List<Integer> courses;
    private LinkedHashMap<Integer, String> courseOptions;

    public StudentForm(LinkedHashMap<Integer, String> courseOptions) {
        this.courseOptions = courseOptions;
    }

    public LinkedHashMap<Integer, String> getCourseOptions() {
        return courseOptions;
    }

    public void setCourseOptions(LinkedHashMap<Integer, String> courseOptions) {
        this.courseOptions = courseOptions;
    }

    public List<Integer> getCourses() {
        return courses;
    }

    public void setCourses(List<Integer> courses) {
        this.courses = courses;
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
}
