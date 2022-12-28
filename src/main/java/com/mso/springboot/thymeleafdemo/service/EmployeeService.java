package com.mso.springboot.thymeleafdemo.service;


import com.mso.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int theId);

    void save(Employee theEmployee);

    void deleteById(int theId);

    List<Employee> searchBy(String theFirstName, String theLastName);
}
