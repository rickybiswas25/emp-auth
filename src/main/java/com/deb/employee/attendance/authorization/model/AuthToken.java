package com.deb.employee.attendance.authorization.model;

/**
 * Created by Deb
 * Date : 18/08/2020
 */
public class AuthToken {
    private final String token;

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
