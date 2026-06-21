package mni.thm.de.webmailclient.auth.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginRequest(

    @field:Schema(
        description = "User email address",
        example = "max.mustermann@exmaple.de"
    )
    @field:Email(message = "Email must be a valid email address")
    @field:NotBlank(message = "Email must not be blank")
    val email: String,

    @field:Schema(
        description = "User password",
        example = "secret123"
    )
    @field:NotBlank(message = "Password must not be blank")
    val password: String,
)