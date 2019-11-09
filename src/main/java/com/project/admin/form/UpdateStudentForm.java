package com.project.admin.form;

import com.project.entity.Course;
import com.project.entity.Student;
import com.project.entity.UserStatus;
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
import java.util.stream.Collectors;

public class UpdateStudentForm {
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
    @NotBlank(message = "Required")
    private String username;
    @NotNull(message = "Required")
    private UserStatus status;
    private LinkedHashMap<Integer, String> courseOptions;

    public UpdateStudentForm(LinkedHashMap<Integer, String> courseOptions) {
        this.courseOptions = courseOptions;
    }

    public LinkedHashMap<Integer, String> getCourseOptions() {
        return courseOptions;
    }

    public void setCourseOptions(LinkedHashMap<Integer, String> courseOptions) {
        this.courseOptions = courseOptions;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<Integer> getCourses() {
        return courses;
    }

    public void setCourses(List<Integer> courses) {
        this.courses = courses;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setStudentData(Student student) {
        this.studentFullName = student.getStudentFullName();
        this.dateOfBirth = student.getDateOfBirth();
        this.contactNumber = student.getContactNumber();
        this.address = student.getAddress();
        this.status = student.getUser().getUserStatus();
        this.username = student.getUser().getUsername();
        this.courses = student.getCourses()
                                .stream()
                                .map(Course::getCourseID)
                                .collect(Collectors.toList());
    }
}
