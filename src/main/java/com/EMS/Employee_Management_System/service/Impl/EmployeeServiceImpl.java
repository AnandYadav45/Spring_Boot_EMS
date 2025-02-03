package com.EMS.Employee_Management_System.service.Impl;

import com.EMS.Employee_Management_System.dto.request.employeeRequest;
import com.EMS.Employee_Management_System.entity.Address;
import com.EMS.Employee_Management_System.entity.Department;
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
    public List<Employee> getByDepartmentName(String department) {

        try {
            List<Employee> emp = employeeRepository.findByDepartmentName(department);
            return emp;

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> getByCityName(String city) {

        try {
            List<Employee> emp = employeeRepository.findByCityName(city);
            return emp;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String create(employeeRequest request) {

        try {
            if(employeeRepository.existsByEmail(request.getEmail())){
                return "Email address is already exist";
            }

            if(employeeRepository.existsByMobile(request.getMobile())){
                return "Mobile number is already exist";
            }

            Employee emp = new Employee();

            emp.setFirstName(request.getFirstName());
            emp.setLastName(request.getLastName());
            emp.setJobTitle(request.getJobTitle());
            emp.setJoiningDate(request.getJoiningDate());
            emp.setEmail(request.getEmail());
            emp.setMobile(request.getMobile());
            emp.setSalary(request.getSalary());


            List<Address> addresses = request.getAddress()
                    .stream()
                    .map(add ->{

                        Address address = new Address();

                        address.setLandmark(add.getLandmark());
                        address.setPostalCode(add.getPostalCode());
                        address.setCity(add.getCity());
                        address.setState(add.getState());
                        address.setCountry(add.getCountry());

                        address.setEmployee(emp);
                        return address;

                    })
                    .collect(Collectors.toList());

            emp.setAddress(addresses);

            List<Department> departments = request.getDepartment()
                    .stream()
                    .map(depart -> {
                        Department department = new Department();

                        department.setDepartmentName(depart.getDepartmentName());
                        department.setEmployee(emp);
                        return department;
                    })
                    .collect(Collectors.toList());

            emp.setDepartment(departments);


            employeeRepository.save(emp);

            return "Employee created successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String update(Employee employee, Long id) {

        try {
            Employee existing_emp = employeeRepository.findById(id)
                    .orElseThrow(()-> new UsernameNotFoundException("Employee does not exist with" + id));

            existing_emp.setFirstName(employee.getFirstName());
            existing_emp.setLastName(employee.getLastName());
            existing_emp.setJobTitle(employee.getJobTitle());
            existing_emp.setJoiningDate(employee.getJoiningDate());
            existing_emp.setEmail(employee.getEmail());
            existing_emp.setMobile(employee.getMobile());
            existing_emp.setSalary(employee.getSalary());

            employeeRepository.save(existing_emp);

            return "Employee update successfully";
        } catch (UsernameNotFoundException e) {

            throw new RuntimeException(e);
        }
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
