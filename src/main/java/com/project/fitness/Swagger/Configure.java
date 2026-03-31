package com.project.fitness.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configure {

    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Fitness Tracking API'S")
                .version("v1.0")
                .description("This API provides a complete backend solution for a Fitness Tracking Application. "
                        + "It allows users to register, authenticate, and manage their fitness activities such as workouts, diet plans, and progress tracking. "
                        + "The system includes secure JWT-based authentication and role-based authorization for users and admins.\n\n"

                        + "🔹 Key Features:\n"
                        + "- User Registration & Login (JWT Authentication)\n"
                        + "- Role-based Access (Admin/User)\n"
                        + "- Workout Management (Add, Update, Delete, View)\n"
                        + "- Diet Plan Management\n"
                        + "- Daily Activity Tracking\n"
                        + "- Progress Monitoring & Reports\n\n"

                        + "🔐 Security:\n"
                        + "- All protected APIs require a valid JWT token\n"
                        + "- Token must be passed in Authorization header as: Bearer <token>\n\n"

                        + "🚀 This API is designed using Spring Boot, Spring Security, and RESTful principles.")

                .contact(new Contact()
                        .name("Harshvardhan")
                        .email("vathare.harsh45@gmail.com"))

                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0"))
        );
    }
}
