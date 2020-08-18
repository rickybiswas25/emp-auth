package com.deb.employee.attendance.authorization.repository;

import com.deb.employee.attendance.authorization.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Deb
 * Date : 17/08/2020
 */
@Repository
public interface EmpAuthRepository extends CrudRepository<Employee, Long> {
    Optional<Employee> findByName(String username);
}
