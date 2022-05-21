package org.example.repository;

import org.example.entity.UserOnlyNameEmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOnlyNameEmailEntityRepository extends JpaRepository<UserOnlyNameEmailEntity, Long> {
}