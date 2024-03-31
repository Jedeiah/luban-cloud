package com.jedeiah.doc;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AutoConfigureBefore(SpringDocConfiguration.class)
public class DocAutoConfiguration {

    private static final String TOKEN_HEADER = "AuthorizationJwt";

    @Value("${spring.application.name:#{null}}")
    private String applicationName;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes(TOKEN_HEADER,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .name("JWT")
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                        ))
                .info(
                        new Info()
                                .title(applicationName)
                                .description(applicationName + "的api文档")
                                .contact(new Contact().name("chj").email("jedeiah@163.com").url(""))
                                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                                .version("0.1")
                );
    }

    @Bean
    public LabelCommandLineRunner labelCommandLineRunner(ApplicationContext applicationContext) {
        return new LabelCommandLineRunner(applicationContext);
    }
}
