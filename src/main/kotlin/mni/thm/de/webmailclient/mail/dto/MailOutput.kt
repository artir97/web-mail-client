package mni.thm.de.webmailclient.mail.dto

import mni.thm.de.webmailclient.mail.Mail
import mni.thm.de.webmailclient.mail.MailStatus
import java.time.Instant
import java.util.UUID

data class MailOutput(
    val id: UUID,
    val ownerId: UUID,
    val sender: String,
    val to: String,
    val cc: String,
    val bcc: String,
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
        sentAt = sentAt,
    )
}