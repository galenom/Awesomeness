package com.solstice.employee.Employee.services;

import com.solstice.employee.Employee.models.Employee;
import com.solstice.employee.Employee.models.EmployeeEntity;
import com.solstice.employee.Employee.repositories.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository repository;

    EmployeeService employeeService;

    @Before
    public void setUpService() { this.employeeService = new EmployeeService( repository ); }

    @Test
    public void getEmployeeTest() {
        EmployeeEntity entity = new EmployeeEntity("Mario", "Galeno", 1L, "111", "Technical Analyst", "mgaleno@solstice.com", "http://test.com");
        when(repository.findEmployeeByEmployeeNumber(1L)).thenReturn(entity);

        Employee employee = employeeService.getEmployee(1L);

        assertEquals(employee.getFirstName(), "Mario");
    }

    @Test
    public void getNonExistingEmployeeTest() {
        when(repository.findEmployeeByEmployeeNumber(0L)).thenReturn(null);

        Employee employee = employeeService.getEmployee(0L);

        assertNull(employee);
    }

}
