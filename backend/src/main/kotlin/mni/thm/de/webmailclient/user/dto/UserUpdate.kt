package mni.thm.de.webmailclient.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserUpdate (
    @field:NotBlank(message = "Email must not be blank")
    @field:Email(message = "Has to be a valid email address")
    val email: String,

    @field:NotBlank(message = "First name must not be blank")
    val firstName: String,

    @field:NotBlank(message = "Last name must not be blank")
    val lastName: String,
)