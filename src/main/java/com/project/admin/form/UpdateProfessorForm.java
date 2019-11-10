package com.project.admin.form;

import com.project.entity.Professor;
import com.project.entity.UserStatus;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class UpdateProfessorForm {
    @NotBlank(message = "Required")
    private String professorFullName;
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

    public String getProfessorFullName() {
        return professorFullName;
    }

    public void setProfessorFullName(String professorFullName) {
        this.professorFullName = professorFullName;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
    public void setProfessorData(Professor professor){
        this.professorFullName = professor.getProfessorFullName();
        this.dateOfBirth = professor.getDateofBirth();
        this.contactNumber = professor.getContactNumber();
        this.address = professor.getAddress();
        this.setUsername(professor.getUser().getUsername());
        this.setStatus(professor.getUser().getUserStatus());
    }
}
