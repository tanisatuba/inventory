package com.flagship.service;

import com.flagship.dto.request.LoginRequest;
import com.flagship.dto.request.SignUpRequest;
import com.flagship.dto.response.LoginResponse;
import com.flagship.dto.response.SignUpResponse;

public interface AuthService {
  SignUpResponse signUp(SignUpRequest signUpRequest);

  LoginResponse login(LoginRequest loginRequest);
}
