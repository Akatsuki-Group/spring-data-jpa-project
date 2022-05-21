package org.example.repository;

import org.example.dto.UserDto;
import org.example.entity.UserExtend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserExtendRespoitory extends JpaRepository<UserExtend, Long> {
    @Query("select new org.example.dto.UserDto(CONCAT(u.name,'JK123'),u.email,e.idCard) from User u,UserExtend e where u.id= e.userId and u.id=:id")
    UserDto findByUserDtoId(@Param("id") Long id);
}
