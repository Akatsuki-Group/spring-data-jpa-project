package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories(queryLookupStrategy= QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
@SpringBootApplication
public class JpaTransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaTransactionApplication.class, args);
    }
}
