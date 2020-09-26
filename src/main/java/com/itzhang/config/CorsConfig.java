package com.itzhang.config;

import io.swagger.models.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 允许所有域名
                .allowedMethods("*") // 允许所有方法
                .allowCredentials(true) // 允许携带认证信息
                .maxAge(3600)
                .allowedHeaders("*"); // 允许所有头
    }
}
