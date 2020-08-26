package com.deb.employee.attendance.authorization.controller;

import com.deb.employee.attendance.authorization.model.AuthToken;
import com.deb.employee.attendance.authorization.model.EmpAuthValue;
import com.deb.employee.attendance.authorization.service.EmpUserDetailsService;
import com.deb.employee.attendance.authorization.utils.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Deb
 * Date : 17/08/2020
 */
@RestController
@CrossOrigin
public class EmpAuthController {
    private final AuthenticationManager authManager;
    private final JwtTokenUtil tokenUtil;
    private final EmpUserDetailsService userDetailService;

    public EmpAuthController(AuthenticationManager authManager, JwtTokenUtil tokenUtil, EmpUserDetailsService userDetailService) {
        this.authManager = authManager;
        this.tokenUtil = tokenUtil;
        this.userDetailService = userDetailService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> getAuthenticate(@RequestBody EmpAuthValue authValue) throws Exception {
        authenticate(authValue.getUsername(), authValue.getPassword());
        UserDetails userDetailService = this.userDetailService.loadUserByUsername(authValue.getUsername());
        String token = this.tokenUtil.generateToken(userDetailService);
        return ResponseEntity.ok(new AuthToken("Bearer " + token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            this.authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
