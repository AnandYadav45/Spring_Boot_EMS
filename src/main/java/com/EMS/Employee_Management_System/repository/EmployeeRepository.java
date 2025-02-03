package com.EMS.Employee_Management_System.repository;

import com.EMS.Employee_Management_System.entity.Employee;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {


    Optional<Employee> findByEmail(String email);


    @Query("SELECT e FROM Employee e JOIN e.department d WHERE d.departmentName = :departmentName")
    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);


    @Query("SELECT e FROM Employee e JOIN e.address a WHERE a.city = :city")
    List<Employee> findByCityName(@Param("city") String city);


    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);
}
