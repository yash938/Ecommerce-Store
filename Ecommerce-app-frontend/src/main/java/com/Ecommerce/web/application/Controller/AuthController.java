package com.Ecommerce.web.application.Controller;

import com.Ecommerce.web.application.Dto.JwtRequest;
import com.Ecommerce.web.application.Dto.JwtResponse;
import com.Ecommerce.web.application.Dto.UserDto;
import com.Ecommerce.web.application.Security.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper helper;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtRequest jwtRequest){
        log.info("username {} and password {}",jwtRequest.getEmail(),jwtRequest.getPassword());
        this.doAuthenticate(jwtRequest.getEmail(),jwtRequest.getPassword());
        UserDetails user = userDetailsService.loadUserByUsername(jwtRequest.getEmail());

        String token = helper.generateToken(user);

        JwtResponse tokenCreated = JwtResponse.builder().token(token).user(modelMapper.map(user, UserDto.class)).build();
        return new ResponseEntity<>(tokenCreated, HttpStatus.CREATED);
    }

    private void doAuthenticate(String email, String password) {
        try{
            Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
        }catch (BadCredentialsException ex){
            ex.getMessage();
        }
    }
    }

