package org.example.data.es.demo.es;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = "org.example.data.es.demo.es")
@Configuration
public class ElasticSearchConfiguration {
}
