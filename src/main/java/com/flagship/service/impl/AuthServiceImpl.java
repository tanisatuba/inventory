package com.flagship.service.impl;

import com.flagship.constant.enums.Gender;
import com.flagship.dto.request.LoginRequest;
import com.flagship.dto.request.SignUpRequest;
import com.flagship.dto.response.LoginResponse;
import com.flagship.dto.response.SignUpResponse;
import com.flagship.exception.PasswordException;
import com.flagship.exception.UserExistException;
import com.flagship.exception.UserNotFoundException;
import com.flagship.model.db.User;
import com.flagship.repository.UserRepository;
import com.flagship.service.AuthService;
import com.flagship.util.DateUtil;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;

  @Autowired
  public AuthServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public SignUpResponse signUp(SignUpRequest signUpRequest) {
    if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
      throw new UserExistException(HttpStatus.SC_CONFLICT, "User is Exist");
    }
    signUpRequest.validate();
    User user = new User();
    user.setName(signUpRequest.getName());
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(signUpRequest.getPassword());
    user.setDateOfBirth(DateUtil.getZoneDateTime(dateConversion(signUpRequest.getDateOfBirth()) + "T00:00:00"));
    user.setGender(Gender.fromName(signUpRequest.getGender().toString()));
    userRepository.save(user);
    return SignUpResponse.from("Sign up successful. You can login now.");
  }

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
    if (user.isPresent()) {
      if (!user.get().getPassword().equals(loginRequest.getPassword())) {
        throw new PasswordException("Password Not Match");
      }
    } else {
      throw new UserNotFoundException("User Name not Found");
    }
    return LoginResponse.from("Login Success", user.get());
  }

  private String dateConversion(String date) {
    DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate parsedDate = LocalDate.parse(date, originalFormatter);
    DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    System.out.println(parsedDate.format(newFormatter));
    return parsedDate.format(newFormatter);
  }
}
