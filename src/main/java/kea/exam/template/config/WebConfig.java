package kea.exam.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    private static final String[] ALLOWED_ORIGINS = new String[]{
            "http://localhost:5173",
            "http://localhost:5174"
    };

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(ALLOWED_ORIGINS)
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}
