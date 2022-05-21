package org.example.repository;

import org.example.entity.UserJson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJsonRepository extends JpaRepository<UserJson, Long> {
}
