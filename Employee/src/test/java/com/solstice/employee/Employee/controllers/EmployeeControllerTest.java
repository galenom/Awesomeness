package com.solstice.employee.Employee.controllers;

import com.solstice.employee.Employee.models.Employee;
import com.solstice.employee.Employee.services.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getEmployeeReturns200OK() throws Exception {
        Employee expectedEmployee = new Employee("Kunal","Shah",1L,"111","Technical Analyst","kshah@solstice.com","/pic.gif");
        when(employeeService.getEmployee(1L)).thenReturn(expectedEmployee);

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeNumber", Matchers.is(1)));
    }

    @Test
    public void getEmployeeNotFoundReturn404() throws Exception {
        when(employeeService.getEmployee(0L)).thenReturn(null);
        mockMvc.perform(get("/api/employees/21"))
                .andExpect(status().isNotFound());

    }


}
