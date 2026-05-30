package mni.thm.de.webmailclient.auth

import jakarta.validation.Valid
import mni.thm.de.webmailclient.auth.dto.LoginRequest
import mni.thm.de.webmailclient.auth.dto.LoginResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody loginRequest: LoginRequest
    ): LoginResponse {
        return authService.login(loginRequest)
    }
}