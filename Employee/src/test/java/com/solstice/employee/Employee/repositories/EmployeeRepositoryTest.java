package com.solstice.employee.Employee.repositories;

import com.solstice.employee.Employee.models.EmployeeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void findEmployeeByIdTest(){
        repository.save(getMockedEmployee());
        EmployeeEntity employee = repository.findEmployeeByEmployeeNumber(1L);
        assertEquals(employee.getFirstName(), "Kunal");

    }

    @Test
    public void findEmployeeThatDoesNotExistById(){
        repository.save(getMockedEmployee());
        EmployeeEntity employee = repository.findEmployeeByEmployeeNumber(21L);
        assertEquals(employee, null);
    }

    private List<EmployeeEntity> getMockedEmployee() {
        return Arrays.asList(
                new EmployeeEntity("Kunal","Shah",1L,"111","Technical Analyst","kshah@solstice.com","/pic.gif")
        );
    }
}
