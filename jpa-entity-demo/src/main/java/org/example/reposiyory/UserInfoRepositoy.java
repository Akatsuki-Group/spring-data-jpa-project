package org.example.reposiyory;

//public interface UserInfoRepositoy extends JpaRepository<UserInfo, UserInfoID> {
//}

import org.example.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepositoy extends JpaRepository<UserInfo, Long> {
}
