package com.flagship.controller;

import com.flagship.dto.response.GetUsers;
import com.flagship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  ResponseEntity<GetUsers> getAllUser() {
    GetUsers getAllUserResponses = userService.getAllUser();
    return new ResponseEntity<>(getAllUserResponses, HttpStatus.OK);
  }
}
