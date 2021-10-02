package com.winble.server.adapter.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * 스웨거 설정
 * @author hasunzo
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final String title = "Winble Api Documentation";
    private final String description = "Winble Api Documentation";
    private final String pathUri = "/v1/**";
    private final String version = "v1";

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())    // 모든 RequestMapping URI 추출
                .paths(PathSelectors.ant(pathUri))     // 경로 패턴 URI만 추출
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .build();
    }
}
