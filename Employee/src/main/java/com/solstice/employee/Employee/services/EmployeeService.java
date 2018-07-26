package com.solstice.employee.Employee.services;

import com.solstice.employee.Employee.models.Employee;
import com.solstice.employee.Employee.models.EmployeeEntity;
import com.solstice.employee.Employee.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getEmployee(Long l) {
        EmployeeEntity entity = employeeRepository.findEmployeeByEmployeeNumber(l);

        if (entity == null) {
            return null;
        }

        return new Employee(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmployeeNumber(),
                entity.getOffice(),
                entity.getTitle(),
                entity.getEmail(),
                entity.getImageUrl()
        );
    }
}
