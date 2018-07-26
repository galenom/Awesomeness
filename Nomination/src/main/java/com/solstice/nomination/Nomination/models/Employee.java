package com.solstice.nomination.Nomination.models;

import lombok.AllArgsConstructor;
import lombok.Data;

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