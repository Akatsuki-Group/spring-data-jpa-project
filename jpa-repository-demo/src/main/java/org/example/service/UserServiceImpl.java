package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    UserRepository userRepository;

    public void testJpa() {
        userRepository.deleteAll();
        userRepository.findAll();
        List<User> users = userRepository.findByEmail("zjk@126.com");
    }

    public void test01() {
        //查询user里面的lastname=jk的第一页，每页大小是20条；并会返回一共有多少页的信息
        //Page<User> page = userRepository.findByLastname("jk", PageRequest.of(1, 20));
        //查询user里面的lastname=jk的第一页的20条数据，不知道一共多少条
        Slice<User> slice = userRepository.findByLastname("jk", PageRequest.of(1, 20));
        //查询出来所有的user里面的lastname=jk的User数据，并按照name正序返回List
        List<User> users = userRepository.findByLastname("jk", Sort.by(Sort.Direction.ASC, "name"));
        //按照createdAt倒序，查询前一百条User数据
        //List<User> userList = userRepository.findByLastname("jk", PageRequest.of(0, 100, Sort.Direction.DESC, "createdAt"));
    }
}