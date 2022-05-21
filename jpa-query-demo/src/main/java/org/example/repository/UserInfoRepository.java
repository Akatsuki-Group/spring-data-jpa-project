package org.example.repository;

import org.example.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

//    @Query(value = "select * from user_info where first_name=?1", nativeQuery = true)
//    List<UserInfo> findByFirstName(String firstName, Sort sort);

    @Query(value = "select * from user_info where first_name=?1 order by ?2", nativeQuery = true)
    List<UserInfo> findByFirstName(String firstName, String sort);

    @Query(value = "select * from user_info where first_name=?1 /* #pageable# */",
            countQuery = "select count(*) from user_info where first_name=?1",
            nativeQuery = true)
    Page<UserInfo> findByFirstName(String firstName, Pageable pageable);
}
