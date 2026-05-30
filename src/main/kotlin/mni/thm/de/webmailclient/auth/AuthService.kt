package mni.thm.de.webmailclient.auth

import mni.thm.de.webmailclient.auth.dto.LoginRequest
import mni.thm.de.webmailclient.auth.dto.LoginResponse
import mni.thm.de.webmailclient.user.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AuthService (
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun login(loginRequest: LoginRequest): LoginResponse {
        val user = userRepository.findByEmailIgnoreCase(loginRequest.email.trim())
            .orElseThrow {
                ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password"
                )
            }

        val passwordMatches = passwordEncoder.matches(
            loginRequest.password,
            user.password
        )

        if (!passwordMatches) {
            throw ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Invalid email or password"
            )
        }

        return LoginResponse(
            message = "Login successful"
        )
    }
}