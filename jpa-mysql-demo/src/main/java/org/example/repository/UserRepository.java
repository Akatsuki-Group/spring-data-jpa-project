package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //根据名称进行查询用户列表
    List<User> findByName(String name);

    // 根据用户的邮箱和名称查询
    List<User> findByEmailAndName(String email, String name);
}