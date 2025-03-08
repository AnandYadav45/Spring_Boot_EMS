package com.EMS.Employee_Management_System.controller;

import com.EMS.Employee_Management_System.dto.request.authRequest;
import com.EMS.Employee_Management_System.dto.request.userRequest;
import com.EMS.Employee_Management_System.repository.UserRepository;
import com.EMS.Employee_Management_System.service.AuthService;
import com.EMS.Employee_Management_System.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    private final UserRepository userRepository;


    private final JwtService jwtService;

    public AuthController(AuthService authService, UserRepository userRepository, JwtService jwtService) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @GetMapping("/public")
    public String GetApp(){
        return "Applixcation is running";
    }


    @PostMapping("/public/login")
    public ResponseEntity<String> Login(@RequestBody authRequest user){
        String response = authService.Login(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/public/register")
  //  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> register(@RequestBody userRequest register){


        String response = authService.Register(register);
        return ResponseEntity.ok(response);
    }



}
