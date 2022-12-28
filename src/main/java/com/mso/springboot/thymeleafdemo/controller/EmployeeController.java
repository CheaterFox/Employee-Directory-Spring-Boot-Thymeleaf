package com.mso.springboot.thymeleafdemo.controller;

import com.mso.springboot.thymeleafdemo.entity.Employee;
import com.mso.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        List<Employee> theEmployees = employeeService.findAll();

        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(
            @ModelAttribute("employee") Employee theEmployee) {

            employeeService.save(theEmployee);

            // use a redirect to prevent duplicate submissions
            return "redirect:/employees/list";
        }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,
                                    Model theModel) {

        Employee theEmployee = employeeService.findById(theId);

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        employeeService.deleteById(theId);

        return "redirect:/employees/list";

    }

    @GetMapping("/search")
    public String search(@RequestParam("firstName") String theFirstName,
                         @RequestParam("lastName") String theLastName,
                         Model theModel) {

        // check names, if both are empty then just give list of all employees

        if (theFirstName.trim().isEmpty() && theLastName.trim().isEmpty()) {
            return "redirect:/employees/list";
        }
        else {
            List<Employee> theEmployees =
                    employeeService.searchBy(theFirstName, theLastName);

            theModel.addAttribute("employees", theEmployees);

            return "employees/list-employees";
        }

    }


}
