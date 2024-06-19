package com.example.simplememo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

  @Value("${api.endpoint}")
  private String apiEndpoint;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    // apiEndpointの値をログに出力して確認
    logger.info("Configured API Endpoint for CORS: {}", apiEndpoint);

    registry.addMapping("/api/**")
            .allowedOrigins(apiEndpoint)
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*")
            .allowCredentials(true);
  }
}
