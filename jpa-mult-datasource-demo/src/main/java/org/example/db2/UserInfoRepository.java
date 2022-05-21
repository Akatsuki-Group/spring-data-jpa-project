package org.example.db2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    Optional<UserInfo> findById(Long userId);

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    UserInfo saveAndFlush(UserInfo entity);
}
