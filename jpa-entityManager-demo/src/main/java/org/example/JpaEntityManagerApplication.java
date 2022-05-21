package org.example;

import org.example.repository.CustomerBaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author tian
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryImplementationPostfix = "Impl",repositoryBaseClass = CustomerBaseRepository.class)
public class JpaEntityManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaEntityManagerApplication.class, args);
    }
}
