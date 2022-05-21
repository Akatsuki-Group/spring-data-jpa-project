package org.example.data.es.demo.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "org.example.data.es.demo.jpa")
@Configuration
public class JpaConfiguration {
}
