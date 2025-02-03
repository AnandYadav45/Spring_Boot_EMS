package com.EMS.Employee_Management_System.dto.request;

import com.EMS.Employee_Management_System.entity.Address;
import com.EMS.Employee_Management_System.entity.Department;
import lombok.*;

import java.time.LocalDate;
import java.util.List;



public class employeeRequest {

    private String firstName;
    private String lastName;
    private String jobTitle;
    private String email;
    private String mobile;
    private LocalDate joiningDate;
    private double salary;
    private List<Address> address;
    private List<Department> department;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<Department> getDepartment() {
        return department;
    }

    public void setDepartment(List<Department> department) {
        this.department = department;
    }

    public employeeRequest(String firstName, String lastName, String jobTitle, String email, String mobile, LocalDate joiningDate, double salary, List<Address> address, List<Department> department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.email = email;
        this.mobile = mobile;
        this.joiningDate = joiningDate;
        this.salary = salary;
        this.address = address;
        this.department = department;
    }


    public employeeRequest() {
    }
}
