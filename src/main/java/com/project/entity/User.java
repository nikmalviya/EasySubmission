package com.project.entity;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userID;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name="user_type")
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private UserStatus userStatus;

    public User(){
    }

    public User(String username, String password, UserType userType, UserStatus userStatus) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.userStatus = userStatus;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }
}
