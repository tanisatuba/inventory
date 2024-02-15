package com.flagship.controller;

import com.flagship.dto.request.LoginRequest;
import com.flagship.dto.request.SignUpRequest;
import com.flagship.dto.response.LoginResponse;
import com.flagship.dto.response.SignUpResponse;
import com.flagship.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping(
          value = "/signup",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<SignUpResponse> signUp(@Valid @NotNull @RequestBody SignUpRequest request) {
    SignUpResponse response = authService.signUp(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(
          value = "/login",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<LoginResponse> login(@Valid @NotNull @RequestBody LoginRequest request) {
    LoginResponse response = authService.login(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
