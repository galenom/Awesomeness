package com.solstice.employee.Employee.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

    private String firstName;
    private String lastName;
    @Id
    @GeneratedValue
    private Long employeeNumber;
    private String office;
    private String title;
    private String email;
    private String imageUrl;


//    public EmployeeEntity(String firstName, String lastName, Long employeeNumber, String office, String title, String email, String imageUrl) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.employeeNumber = employeeNumber;
//        this.office = office;
//        this.title = title;
//        this.email = email;
//        this.imageUrl = imageUrl;
//    }
}
