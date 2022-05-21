package org.example.repository;

import org.example.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>, QuerydslPredicateExecutor<UserInfo> {
    @Override
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<UserInfo> findById(Long userId);

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    UserInfo saveAndFlush(UserInfo org.example.entity);
}