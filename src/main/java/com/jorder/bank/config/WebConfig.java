package com.jorder.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class WebConfig {
    
    @Autowired
    private Dotenv dotenv;

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry){
                String allowedOrigin = dotenv.get("ALLOWED_ORIGIN");
                corsRegistry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200", allowedOrigin)
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);

            }
        };
    }

}
