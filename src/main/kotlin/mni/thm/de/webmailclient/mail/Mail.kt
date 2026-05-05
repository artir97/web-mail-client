package mni.thm.de.webmailclient.mail

import java.util.UUID
import kotlin.time.Clock
import kotlin.time.Instant

data class Mail(
    val id: UUID = UUID.randomUUID(),
    val ownerId: UUID,
    val sender: String,
    val to: List<String>,
    val cc: List<String> = emptyList(),
    val bcc: List<String> = emptyList(),
    val subject: String,
    val body: String,
    val status: MailStatus = MailStatus.DRAFT,
    val createdAt: Instant = Clock.System.now(),
    val sentAt: Instant? = null,
)
