package com.EMS.Employee_Management_System.repository;

import com.EMS.Employee_Management_System.entity.Employee;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {


    Optional<Employee> findByEmail(String email);
}
