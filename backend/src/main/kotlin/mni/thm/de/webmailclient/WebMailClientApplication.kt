package mni.thm.de.webmailclient

import io.swagger.v3.oas.annotations.ExternalDocumentation
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(
    info = Info(
        title = "WebMailClient API",
        version = "1.0.0",
        description = "API for user management, authentication, mails and attachments.",
        contact = Contact(
            name = "WebMailClient Team",
            email = "support@example.com"
        ),
        license = License(
            name = "Proprietary"
        )
    ),
    servers = [
        Server(
            url = "http://localhost:8080",
            description = "Local development server"
        )
    ],
    externalDocs = ExternalDocumentation(
        description = "Project documentation",
        url = "https://example.com/docs"
    )
)
@SpringBootApplication
class WebMailClientApplication

fun main(args: Array<String>) {
    runApplication<WebMailClientApplication>(*args)
}