package org.example.repository;

import org.example.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{
    @Override
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<UserInfo> findById(Long userId);

    @Query("From UserInfo where id=2")
    List<UserInfo> queryByFlushTest();
}