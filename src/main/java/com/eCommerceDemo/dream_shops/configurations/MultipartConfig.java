package com.eCommerceDemo.dream_shops.configurations;

import org.springframework.boot.web.servlet.MultipartConfigFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.util.unit.DataSize;


@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("10MB")); // تحديد الحد الأقصى لحجم الملف
        factory.setMaxRequestSize(DataSize.parse("10MB")); // تحديد الحد الأقصى لحجم الطلب
        return factory.createMultipartConfig();
    }
}
