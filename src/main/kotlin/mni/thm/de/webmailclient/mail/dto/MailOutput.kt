package mni.thm.de.webmailclient.mail.dto

import mni.thm.de.webmailclient.mail.Mail
import mni.thm.de.webmailclient.mail.MailStatus
import java.util.UUID
import java.time.Instant

data class MailOutput(
    val id: UUID,
    val ownerId: UUID,
    val sender: String,
    val to: List<String>,
    val cc: List<String>,
    val bcc: List<String> = emptyList(),
    val subject: String,
    val body: String,
    val status: MailStatus,
    val createdAt: Instant,
    val sentAt: Instant?,
)

fun Mail.toOutput(): MailOutput {
    return MailOutput(
        id = id,
        ownerId = ownerId,
        sender = sender,
        to = to,
        cc = cc,
        bcc = bcc,
        subject = subject,
        body = body,
        status = status,
        createdAt = createdAt,
        sentAt = sentAt
    )
}