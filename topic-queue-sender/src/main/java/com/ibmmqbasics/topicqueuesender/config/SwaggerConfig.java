package com.ibmmqbasics.topicqueuesender.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:swagger-messages.properties", encoding = "UTF-8")
public class SwaggerConfig {

    @Value("${api.title}")
    private String tituloApi;
    @Value("${api.description}")
    private String descricaoApi;

    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title(tituloApi)
                .description(descricaoApi)
                .version(getApiVersion());
    }

    private String getApiVersion() {
        String version = buildProperties.getVersion();
        if (version.contains("SNAPSHOT")) {
            version = version.replace("SNAPSHOT", buildProperties.getTime().toString());
        }
        return version;
    }

}
