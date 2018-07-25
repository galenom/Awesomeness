package com.solstice.employee.Employee.repositories;

import com.solstice.employee.Employee.models.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends CrudRepository<EmployeeEntity,Long> {

    EmployeeEntity findEmployeeByEmployeeNumber(long l);
}
