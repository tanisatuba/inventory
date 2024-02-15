package com.flagship.service.impl;

import com.flagship.dto.response.AllUserResponse;
import com.flagship.dto.response.GetUsers;
import com.flagship.model.db.User;
import com.flagship.repository.UserRepository;
import com.flagship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public GetUsers getAllUser() {
    List<User> userList = (List<User>) userRepository.findAll();
    List<AllUserResponse> allUserRespons = new ArrayList<>();
    for (User user : userList) {
      allUserRespons.add(AllUserResponse.from(user));
    }
    return GetUsers.from(allUserRespons);
  }
}
