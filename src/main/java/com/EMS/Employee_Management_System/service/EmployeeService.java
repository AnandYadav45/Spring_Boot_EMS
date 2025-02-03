package com.EMS.Employee_Management_System.service;

import com.EMS.Employee_Management_System.dto.request.employeeRequest;
import com.EMS.Employee_Management_System.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public abstract List<Employee> getAll();

    public abstract Optional<Employee> getById(Long id);

    public abstract List<Employee> getByDepartmentName(String department);

    public abstract List<Employee> getByCityName(String cityName);

    public abstract String create(employeeRequest request);

    public abstract  String update(Employee employee, Long id);

    public abstract String delete(Long id);

}
