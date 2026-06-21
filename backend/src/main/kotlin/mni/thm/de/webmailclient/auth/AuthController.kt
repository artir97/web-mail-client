package mni.thm.de.webmailclient.auth

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import mni.thm.de.webmailclient.auth.dto.LoginRequest
import mni.thm.de.webmailclient.auth.dto.LoginResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(
    name = "Authentication",
    description = "Handle user authentication."
)

@RestController
class AuthController(
    private val authService: AuthService,
) {
    @Operation(
        operationId = "login",
        summary = "Authenticate user",
        description = "Authenticates a user with email and password and returns a JWT token."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Login successful."
    )
    @ApiResponse(
        responseCode = "401",
        description = "Invalid email or password."
    )
    @PostMapping("/login")
    fun login(
        @Valid @RequestBody loginRequest: LoginRequest
    ): LoginResponse {
        return authService.login(loginRequest)
    }
}