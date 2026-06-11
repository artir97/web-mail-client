package mni.thm.de.webmailclient.user.dto

import mni.thm.de.webmailclient.user.User
import java.util.UUID

data class UserOutput(
    val id: UUID,
    val email: String,
    val firstName: String,
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