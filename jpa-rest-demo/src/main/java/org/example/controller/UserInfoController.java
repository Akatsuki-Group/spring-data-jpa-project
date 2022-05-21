package org.example.controller;


import lombok.extern.log4j.Log4j2;
import org.example.dto.UserInfoDto;
import org.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Log4j2
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    //跟进UserId取用户的详细信息
    @GetMapping("/user/{userId}")
    public UserInfoDto findByUserId(@PathVariable Long userId) {
        System.out.println(userId);
        return userInfoService.findByUserId(userId);
    }


}