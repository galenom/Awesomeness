package com.solstice.employee.Employee.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class Employee {

    private String firstName;
    private String lastName;
    private Long employeeNumber;
    private String office;
    private String title;
    private String email;
    private String imageUrl;

    public Employee() {}
}
