package com.deb.employee.attendance.authorization.service;

import com.deb.employee.attendance.authorization.entity.Employee;
import com.deb.employee.attendance.authorization.model.EmployeeUserDetails;
import com.deb.employee.attendance.authorization.repository.EmpAuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Deb
 * Date : 17/08/2020
 */
@Service
public class EmpUserDetailsService implements UserDetailsService {
    private final EmpAuthRepository authRepo;

    public EmpUserDetailsService(EmpAuthRepository authRepo) {
        this.authRepo = authRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = this.authRepo.findByName(username);
        if (employee.isPresent()) {
            return new EmployeeUserDetails(employee.get());
        } else {
            throw new UsernameNotFoundException(String.format("User name not found %s", username));
        }
    }
}
