package ru.crm.maincrmservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "crm")
public class AppConfig {

    /**
     * Дефолтный размер пагинации
     */
    private Integer defaultPageSize;
}