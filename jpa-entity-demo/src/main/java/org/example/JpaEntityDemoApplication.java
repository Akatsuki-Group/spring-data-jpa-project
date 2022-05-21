package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;

@EnableJpaRepositories(queryLookupStrategy= QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
@SpringBootApplication
public class JpaEntityDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaEntityDemoApplication.class, args);
    }
}
