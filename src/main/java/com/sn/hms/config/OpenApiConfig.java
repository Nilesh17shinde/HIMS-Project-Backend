package com.sn.hms.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Hospital Management System",
                description = "API for Hospital Management System",
                termsOfService = "https://swagger.io/terms/",
                contact = @Contact(
                        name = "Nilesh Shinde",
                        email = "nileshshinde1798@gmail.com",
                        url = "https://mail.google.com/mail/u/0/#inbox"
                ),
                license = @License(
                        name = "Apache",
                        url = "https://mail.google.com/mail/u/0/#inbox"
                ),
                version = "V 1.0"
        ),
        externalDocs = @ExternalDocumentation(
                    description = "This is external docs ",
                    url = "https://mail.google.com/mail/u/0/#inbox "
                ),
        servers = {
                @Server(
                        description = "Development Server",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Test Server",
                        url = "http://localhost:8081"
                )
        }
)
public class OpenApiConfig  {

}
