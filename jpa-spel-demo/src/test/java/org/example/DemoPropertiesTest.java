package org.example;

import org.example.properties.DemoProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(TestConfiguration.class)
@ComponentScan(value = "org.example.properties.DemoProperties")
public class DemoPropertiesTest {
    @Autowired(required = false)
    private DemoProperties demoProperties;
    @Test
    public void testSpel() {
        //通过测试用例就可以测试@Value里面不同表达式的值了
        System.out.println(demoProperties.toString());
    }
    @TestConfiguration
    static class TestConfig {
        @Bean
        public DemoProperties demoProperties () {
            return new DemoProperties();
        }
    }
}