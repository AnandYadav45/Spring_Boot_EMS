package com.EMS.Employee_Management_System.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    public abstract String extractUsername(String token);

    public abstract String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);


}
