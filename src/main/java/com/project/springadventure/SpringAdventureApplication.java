package com.project.springadventure;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "KLP Technology API",
        version = "1.0",
        description = """
            This API provides endpoints to manage App Users.
            
            ## Features
            * Create new App Users with auto-generated IDs
            * Retrieve list of all App Users
            * Retrieve a specific App User by ID
            * Input validation for email and user type
            
            ## Authentication
            Currently, this API doesn't require authentication.
            """,
        contact = @Contact(
            name = "Development Team",
            email = "bryan18jonah@gmail.com"
        ),
        license = @License(
            name = "MIT License",
            url = "https://opensource.org/licenses/MIT"
        )
    ),
    servers = {
        @Server(
            url = "http://localhost:8080",
            description = "Local Development Server"
        )
    }
)
public class SpringAdventureApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAdventureApplication.class, args);
    }

}
