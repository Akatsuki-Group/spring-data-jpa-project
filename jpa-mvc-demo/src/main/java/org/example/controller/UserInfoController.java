package org.example.controller;


import com.querydsl.core.types.Predicate;
import org.example.entity.UserInfo;
import org.example.repository.UserInfoRepository;
import org.example.service.UserInfoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserInfoController {
    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 从path变量里面获得参数ID的值，然后直接转化成UserInfo实体
     *
     * @param userInfo
     * @return
     */
    @GetMapping("/user/{id}")
    public UserInfo getUserInfoFromPath(@PathVariable("id") UserInfo userInfo) {
        return userInfo;
    }

    /**
     * 从 request的param中的ID变量值，知己转化成UserInfo实体
     *
     * @param userInfo
     * @return
     */
    @GetMapping("/user")
    public UserInfo getUserInfoFromRequestParam(@RequestParam("id") UserInfo userInfo) {
        return userInfo;
    }

    @GetMapping("/users")
    public Page<UserInfo> queryByPage(Pageable pageable, UserInfo userInfo) {
        return userInfoRepository.findAll(Example.of(userInfo), pageable);
    }

    @GetMapping("/users/sort")
    public HttpEntity<List<UserInfo>> queryBySort(Sort sort, UserInfoInterface userInfoInterface) {
        System.out.println(userInfoInterface);
        return new HttpEntity<>(userInfoRepository.findAll(sort));
    }

    /**
     * 测试Projected的支持
     *
     * @param userInfoInterface
     * @return
     */
    @PostMapping("/users/projected")
    public UserInfoInterface saveUserInfo(@RequestBody UserInfoInterface userInfoInterface) {
        return userInfoInterface;
    }

    /**
     * @param userInfo
     * @return
     */
    @PostMapping("/user/info")
    public UserInfo saveUserInfo(@RequestBody UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @GetMapping(value = "user/dsl")
    Page<UserInfo> index(@QuerydslPredicate(root = UserInfo.class) Predicate predicate, Pageable pageable) {
        return userInfoRepository.findAll(predicate, pageable);
    }
}