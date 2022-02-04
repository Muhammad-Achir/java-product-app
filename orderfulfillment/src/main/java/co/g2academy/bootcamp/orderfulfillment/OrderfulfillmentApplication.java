package co.g2academy.bootcamp.orderfulfillment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class OrderfulfillmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderfulfillmentApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:8080");

//                registry.addMapping("/api/").allowCredentials(false);
                registry.addMapping("/**")
//                        .allowedOrigins("allowedOriginPatterns")
                        .allowedOrigins("http://localhost:3000");
            }
        };
    }
}
