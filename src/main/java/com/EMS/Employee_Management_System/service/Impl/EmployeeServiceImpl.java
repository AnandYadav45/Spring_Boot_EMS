package com.EMS.Employee_Management_System.service.Impl;

import com.EMS.Employee_Management_System.entity.Employee;
import com.EMS.Employee_Management_System.repository.EmployeeRepository;
import com.EMS.Employee_Management_System.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> getAll() {
        try {
            List<Employee> emp = employeeRepository.findAll()
                    .stream()
                    .collect(Collectors.toList());

            return emp;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Employee> getById(Long id) {
        try {
            Optional<Employee> emp = employeeRepository.findById(id);

            return emp;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Employee> getByDepartmentName(String department) {
        return Optional.empty();
    }

    @Override
    public String update(Employee employee, Long id) {

        Employee existing_emp = employeeRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("Employee does not exist with" + id));


        return "";
    }

    @Override
    public String delete(Long id) {

        try {
            Employee emp = employeeRepository.findById(id)
                    .orElseThrow(()-> new UsernameNotFoundException("Employee does not exist with " + id));

            employeeRepository.delete(emp);

            return "Employee delete successfully";

        } catch (UsernameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
