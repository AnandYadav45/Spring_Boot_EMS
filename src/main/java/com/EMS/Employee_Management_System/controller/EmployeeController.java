package com.EMS.Employee_Management_System.controller;


import com.EMS.Employee_Management_System.dto.request.employeeRequest;
import com.EMS.Employee_Management_System.entity.Employee;
import com.EMS.Employee_Management_System.service.EmployeeService;
import jakarta.persistence.GeneratedValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>>  getAllEmployee(){
        logger.info("Fetching all employees");
        List<Employee> employees =  employeeService.getAll();

        return ResponseEntity.ok(employees);
    }


    @GetMapping("")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@RequestParam(name = "emp_id") long id){
        logger.info("Fetching employee with ID: {}", id);
        Optional<Employee> employee = employeeService.getById(id);
        return ResponseEntity.ok(employee);

    }

    @GetMapping("/department")
    public ResponseEntity<List<Employee>> getEmployeeByD_Name(@RequestParam(name = "departmentName") String departmentName){
        logger.info("Fetching employees from department: {}", departmentName);
        List<Employee> emp = employeeService.getByDepartmentName(departmentName);
        return ResponseEntity.ok(emp);

    }

    @GetMapping("/city")
    public ResponseEntity<List<Employee>> getEmployeeByCityName(@RequestParam(name = "city_name") String city_name){
        logger.info("Fetching employees from city: {}", city_name);
        List<Employee> emp = employeeService.getByCityName(city_name);
        return ResponseEntity.ok(emp);

    }

    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody employeeRequest request){
        logger.info("Creating a new employee with details: {}", request);
        String result = employeeService.create(request);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee,@RequestParam Long id){
        logger.info("Updating employee with ID: {}", id);
        String result = employeeService.update(employee,id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id){

        logger.info("Deleting employee with ID: {}", id);
        String result = employeeService.delete(id);
        return ResponseEntity.ok(result);

    }


}
