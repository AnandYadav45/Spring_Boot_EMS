package com.EMS.Employee_Management_System.service.Impl;


import com.EMS.Employee_Management_System.dto.request.authRequest;
import com.EMS.Employee_Management_System.dto.request.userRequest;
import com.EMS.Employee_Management_System.entity.Role;
import com.EMS.Employee_Management_System.entity.User;
import com.EMS.Employee_Management_System.repository.UserRepository;
import com.EMS.Employee_Management_System.service.AuthService;
import com.EMS.Employee_Management_System.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String Login(authRequest userDto) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
            );


            User user = userRepository.findByUsername(userDto.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User does not exist with username: {}"+ userDto.getUsername()));

            String token = jwtService.generateToken(user);

            System.out.println(user.getRole());

            return token;

        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String Register(userRequest register) {

        try {
            if(userRepository.findByUsername(register.getUsername()).isPresent()){
                throw  new RuntimeException("Username already exist");
            }

            User user = new User();
            user.setUsername(register.getUsername());
            user.setPassword(passwordEncoder.encode(register.getPassword()));
            user.setRole(Role.valueOf(register.getRole()));


            userRepository.save(user);

            return "User register successfully";


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }


    }
}
