package mni.thm.de.webmailclient.user

import java.util.UUID

data class User (
    val id: UUID = UUID.randomUUID(),
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
)