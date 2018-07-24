package com.solstice.employee.Employee.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {

    private Employee employee;

    @Before
    public void setUpEmployee() {
        employee = new Employee();
    }

    @Test
    public void getSetEmployeeFirstName() {
        employee.setFirstName("Sam");
        assertEquals(employee.getFirstName(), "Sam");
    }

    @Test
    public void getSetEmployeeLastName() {
        employee.setLastName("Adams");
        assertEquals(employee.getLastName(), "Adams");
    }

    @Test
    public void getSetEmployeeNumber() {
        employee.setEmployeeNumber(10L);
        assertEquals(employee.getEmployeeNumber(), new Long(10));
    }

    @Test
    public void getSetOffice() {
        employee.setOffice("Chicago");
        assertEquals(employee.getOffice(), "Chicago");
    }

    @Test
    public void getSetTitle() {
        employee.setTitle("Manager");
        assertEquals(employee.getTitle(), "Manager");
    }

    @Test
    public void getSetEmail() {
        employee.setEmail("test@test.com");
        assertEquals(employee.getEmail(), "test@test.com");
    }

    public void getSetImageUrl() {
        employee.setImageUrl("https://tiny.url/test");
        assertEquals(employee.getImageUrl(), "https://tiny.url/test");
    }

}

