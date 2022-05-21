package org.example.controller;

import org.example.db1.User;
import org.example.db1.UserRepository;
import org.example.db2.UserInfo;
import org.example.db2.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private UserInfoRepository userInfoRepository;
   //操作user的Repository
   @PostMapping("/user")
   public User saveUser(@RequestBody User user) {
      return userRepository.save(user);
   }
   //操作userInfo的Repository
  @PostMapping("/user/info")
  public UserInfo saveUserInfo(@RequestBody UserInfo userInfo) {
     return userInfoRepository.save(userInfo);
  }
}