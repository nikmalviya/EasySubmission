package com.project.admin.form;

import com.project.entity.UserStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AdminForm {
    @NotBlank(message = "Required")
    private String username;
    @NotBlank(message = "Required")
    private String password;
    @NotBlank(message = "Required")
    private String confirmPassword;
    @NotNull(message = "Required")
    private UserStatus status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
