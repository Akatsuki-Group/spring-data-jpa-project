package org.example.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author MSI-NB
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfiguration {
    Boolean swaggerEnabled=true;
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo())
//                // 是否开启
//                .enable(swaggerEnabled).select()
//                // 扫描的路径包
//                .apis(RequestHandlerSelectors.basePackage("org.example.controller"))
//                .apis( RequestHandlerSelectors.basePackage("org.example.repository"))
//                // 指定路径处理PathSelectors.any()代表所有的路径
//                .paths(PathSelectors.any()).build().pathMapping("/");
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("spring data rest")
//                .description("spring data rest")
//                // 作者信息
//                .contact(new Contact("obito", "url", "email"))
//                .version("1.0.0")
//                .build();
//    }
}