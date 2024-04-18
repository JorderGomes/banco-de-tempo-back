package com.jorder.bank.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "file")
@Getter
@Setter
public class FileProperties {
    
    private String uploadDir;
    
}
