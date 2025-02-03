package com.EMS.Employee_Management_System.dto.request;

import com.EMS.Employee_Management_System.entity.Role;



public class userRequest {


    private String username;

    private String password;

    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public userRequest(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
