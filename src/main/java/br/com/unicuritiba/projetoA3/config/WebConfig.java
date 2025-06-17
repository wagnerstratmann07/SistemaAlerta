package br.com.unicuritiba.projetoA3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/send-message")
                .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500", "http://localhost:8080", "http://localhost:8000") // Adicione as origens que você pode usar
                .allowedMethods("POST")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}