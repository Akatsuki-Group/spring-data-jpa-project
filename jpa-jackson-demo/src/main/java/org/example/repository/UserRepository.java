package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
//	@Query(value = "select * from #param",nativeQuery = true)
//	public User querabc(String param);
}
