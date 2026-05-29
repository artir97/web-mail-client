package mni.thm.de.webmailclient.user

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import mni.thm.de.webmailclient.mail.Mail
import java.util.UUID

@Entity
@Table(name = "users")
data class User (
    @Id
    val id: UUID = UUID.randomUUID(),
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,

    @OneToMany(mappedBy = "owner")
    val mails: List<Mail> = emptyList(),
)