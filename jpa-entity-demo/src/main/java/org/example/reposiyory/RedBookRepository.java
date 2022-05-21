package org.example.reposiyory;

import org.example.entity.RedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedBookRepository extends JpaRepository<RedBook, Long> {
}