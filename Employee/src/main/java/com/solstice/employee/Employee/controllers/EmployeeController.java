package com.solstice.employee.Employee.controllers;

import com.solstice.employee.Employee.models.Employee;
import com.solstice.employee.Employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeNumber}")
    public Employee getEmployee(@PathVariable Long employeeNumber){
        Employee employee = employeeService.getEmployee(employeeNumber);
        if(employee == null){
            throw new EmployeeNotFoundException();
            //return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity(employee, HttpStatus.OK);
        return employee;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Solstie Not Found")
    class EmployeeNotFoundException extends RuntimeException {
    }

}
