package com.EMS.Employee_Management_System.service;

import com.EMS.Employee_Management_System.dto.request.authRequest;
import com.EMS.Employee_Management_System.dto.request.userRequest;

public interface AuthService {

    public abstract String Login(authRequest user);

    public abstract String Register(userRequest user);




}
