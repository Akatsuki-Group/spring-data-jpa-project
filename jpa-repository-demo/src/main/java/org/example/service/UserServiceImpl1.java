package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl1 extends BaseServiceImpl<User, Long, UserRepository> {

    public UserServiceImpl1(UserRepository repository) {
        super(repository);
    }
}