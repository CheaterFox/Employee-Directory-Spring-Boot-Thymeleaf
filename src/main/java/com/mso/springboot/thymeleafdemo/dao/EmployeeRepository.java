package com.mso.springboot.thymeleafdemo.dao;


import com.mso.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByOrderByLastNameAsc();

    List<Employee> findByFirstNameContainsAndLastNameContainsAllIgnoreCase(String theFirstName, String theLastName);
}
