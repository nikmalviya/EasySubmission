package com.project.admin.form;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProfessorForm extends AdminForm{
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

}
