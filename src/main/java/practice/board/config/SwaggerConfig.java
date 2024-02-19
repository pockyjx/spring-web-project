package practice.board.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "회원 관리 API 명세서",
                description = "회원 관리 API 명세서",
                version = "v1.0"
        )
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userAPI() {
        return GroupedOpenApi.builder()
                .group("회원 관리")
                .pathsToMatch("/users/**")
                .build();
    }
}
