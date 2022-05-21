package org.example.controller;

import lombok.extern.log4j.Log4j2;
import org.example.entity.UserInfo;
import org.example.help.TransactionHelper;
import org.example.repository.UserInfoRepository;
import org.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Log4j2
public class UserInfoController {
    private TransactionHelper transactionHelper;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/user/info/{id}")
    @Cacheable(value = "userInfo", key = "{#root.methodName, #id}", unless = "#result == null")
    //利用默认key值生成规则value加key生成一个redis的key值，result==null的时候不进行缓存
    public UserInfo getUserInfo(@PathVariable("id") Long id) {
        //第二次就不会再执行这里了
        return userInfoRepository.findById(id).get();
    }

}