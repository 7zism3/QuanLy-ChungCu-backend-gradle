package com.nhom43.quanlychungcubackendgradle;

import com.nhom43.quanlychungcubackendgradle.admin.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class QuanLyChungCuBackendGradleApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuanLyChungCuBackendGradleApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**");
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
//                        .allowedHeaders("header1", "header2", "header3")
//                        .exposedHeaders("header1", "header2")
//                        .allowCredentials(false).maxAge(3600);
            }
        };
    }
}
