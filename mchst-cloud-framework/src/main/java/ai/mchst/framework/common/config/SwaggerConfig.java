package ai.mchst.framework.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"ai.mchst"};
        return GroupedOpenApi.builder().group("MchstCloud")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("ai.mchst");

        return new OpenAPI(SpecVersion.V30).info(new Info()
                .title("MchstCloud")
                .description("MchstCloud")
                .contact(contact)
                .version("1.0")
                .termsOfService("http://mchst.ai")
                .license(new License().name("xxx")
                        .url("http://mchst.ai")));
    }

}