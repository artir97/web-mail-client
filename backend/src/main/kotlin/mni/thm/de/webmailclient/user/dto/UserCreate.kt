package mni.thm.de.webmailclient.user.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import mni.thm.de.webmailclient.user.User


import java.util.UUID

data class UserCreate(
    @field:Schema(
        description = "User email address",
        example = "max.mustermann@example.com"
    )
    @field:NotBlank(message = "Email can't be blank")
    @field:Email(message = "Has to be a valid email address")
    val email: String,


    @field:Schema(
        description = "First name of the user",
        example = "Max",
    )
    @field:NotBlank(message = "First name can't be blank")
    val firstName: String,


    @field:Schema(
        description = "Last name of the user",
        example = "Mustermann",
    )
    @field:NotBlank(message = "Last name can't be blank")
    val lastName: String,


    @field:Schema(
        description = "User password",
        example = "Password123!"
    )
    @field:NotBlank(message = "Password can't be blank")
    @field:Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}$",
        message = "Password must be at least 8 characters long and contain at least one letter, one number, and one special character"
    )
    val password: String,
) {
    fun toUser(id: UUID = UUID.randomUUID()): User {
        return User(
            id = id,
            email = email.trim(),
            firstName = firstName.trim(),
            lastName = lastName.trim(),
            password = password
        )
    }
}
