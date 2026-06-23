package mni.thm.de.webmailclient.user.dto

import io.swagger.v3.oas.annotations.media.Schema
import mni.thm.de.webmailclient.user.User
import java.util.UUID

data class UserOutput(

    @field:Schema(
        description = "Unique user identifier"
    )
    val id: UUID,

    @field:Schema(
        description = "User email address",
        example = "max.mustermann@example.com"
    )
    val email: String,

    @field:Schema(
        description = "User first name",
        example = "Max"
    )
    val firstName: String,

    @field:Schema(
        description = "User last name",
        example = "Mustermann"
    )
    val lastName: String,
)

fun User.toOutput(): UserOutput {
    return UserOutput(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
    )
}