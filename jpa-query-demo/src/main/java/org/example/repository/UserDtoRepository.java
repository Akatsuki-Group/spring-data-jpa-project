package org.example.repository;

import org.example.dto.UserDto;
import org.example.dto.UserOnlyName;
import org.example.dto.UserSimpleDto;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author MSI-NB
 */
public interface UserDtoRepository extends JpaRepository<User, Long> {
    /**
     * 通过query注解根据name查询user信息
     */
    @Query("From User where name=:name")
    User findByQuery(@Param("name") String nameParam);

    /**
     * 查询用户表里面的name、email和UserExtend表里面的idCard
     *
     * @param id
     * @return
     */
    @Query("select u.name,u.email,e.idCard from User u,UserExtend e where u.id= e.userId and u.id=:id")
    List<Object[]> findByUserId(@Param("id") Long id);

    @Query("select new org.example.dto.UserDto(CONCAT(u.name,'JK123'),u.email,e.idCard) from User u,UserExtend e where u.id= e.userId and u.id=:id")
    UserDto findByUserDtoId(@Param("id") Long id);

    //利用接口DTO获得返回结果，需要注意的是每个字段需要as和接口里面的get方法名字保持一样
    @Query("select CONCAT(u.name,'JK123') as name,UPPER(u.email) as email ,e.idCard as idCard from User u,UserExtend e where u.id= e.userId and u.id=:id")
    UserSimpleDto findByUserSimpleDtoId(@Param("id") Long id);


    /**
     * 利用JQPl动态查询用户信息
     *
     * @param name
     * @param email
     * @return UserSimpleDto接口
     */
    @Query("select u.name as name,u.email as email from User u where (:name is null or u.name =:name) and (:email is null or u.email =:email)")
    UserOnlyName findByUser(@Param("name") String name, @Param("email") String email);

    /**
     * 利用原始sql动态查询用户信息
     *
     * @param user
     * @return
     */
    @Query(value = "select u.name as name,u.email as email from user u where (:#{#user.name} is null or u.name =:#{#user.name}) and (:#{#user.email} is null or u.email =:#{#user.email})", nativeQuery = true)
    UserOnlyName findByUser(@Param("user") User user);
}