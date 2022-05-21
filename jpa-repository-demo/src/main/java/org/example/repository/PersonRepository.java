package org.example.repository;

import org.example.entity.User;
import org.example.entity.UserAddress;
import org.springframework.data.repository.Repository;

import java.util.List;

interface PersonRepository extends Repository<User, Long> {
    // and 的查询关系
    List<User> findByAddressAndLastname(UserAddress userAddress, String lastname);

    // 包含 distinct 去重，or 的 sql 语法
    List<User> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);

    // 根据 lastname 字段查询忽略大小写
    List<User> findByLastnameIgnoreCase(String lastname);

    // 根据 lastname 和 firstname 查询 equal 并且忽略大小写
    List<User> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);

    // 对查询结果根据 lastname 排序，正序
    List<User> findByLastnameOrderByFirstnameAsc(String lastname);

    // 对查询结果根据 lastname 排序，倒序
    List<User> findByLastnameOrderByFirstnameDesc(String lastname);


    long countByLastname(String lastname);//查询总数

    long deleteByLastname(String lastname);//根据一个字段进行删除操作，并返回删除行数

    List<User> removeByLastname(String lastname);//根据Lastname删除一堆User,并返回删除的User






}