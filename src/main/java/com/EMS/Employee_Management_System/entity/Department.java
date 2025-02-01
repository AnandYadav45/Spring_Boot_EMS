package com.EMS.Employee_Management_System.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "department", schema = "users")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    private String DepartmentName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;

    public Department(Long id, String departmentName) {
        this.id = id;
        DepartmentName = departmentName;
    }

    public Department() {
    }
}
