package org.example.repository;

import org.example.entity.Address;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

//    List<Address> findByUserIdIn(List<Long> userIds);

    @Override //可以覆盖原始方法，添加上不同的@EntityGraph策略
//使用@EntityGraph查询所有Address的时候，指定name = "getAllUserInfo"的@NamedEntityGraph，采用默认的EntityGraphType.FETCH，如果Address里面有多个关联关系的时候，只有在name = "getAllUserInfo"的实体图配置的userInfo属性上采用Eager模式，其他关联关系属性没有指定，默认采用LAZY模式；
    @EntityGraph(value = "getAllUserInfo")
    List<Address> findAll();
}