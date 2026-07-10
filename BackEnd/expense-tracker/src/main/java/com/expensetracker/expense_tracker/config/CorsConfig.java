package com.expensetracker.expense_tracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private static final String DEFAULT_ORIGIN = "https://accomplished-solace-production-38d2.up.railway.app";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String env = System.getenv("ALLOWED_ORIGINS");

        boolean allowAny = false;
        String[] origins;

        if (env != null && !env.isBlank()) {
            String trimmed = env.trim();
            if ("*".equals(trimmed)) {
                allowAny = true;
                origins = new String[]{"*"};
            } else {
                origins = Arrays.stream(trimmed.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toArray(String[]::new);
            }
        } else {
            origins = new String[]{DEFAULT_ORIGIN};
        }

        var reg = registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);

        if (allowAny) {
            reg.allowedOrigins("*");
            // When allowing any origin, credentials must not be allowed
            reg.allowCredentials(false);
        } else {
            reg.allowedOrigins(origins)
                    .allowCredentials(true);
        }
    }

}
