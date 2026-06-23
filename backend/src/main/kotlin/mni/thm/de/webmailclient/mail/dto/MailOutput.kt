package mni.thm.de.webmailclient.mail.dto

import io.swagger.v3.oas.annotations.media.Schema
import mni.thm.de.webmailclient.attachment.dto.AttachmentOutput
import mni.thm.de.webmailclient.attachment.dto.toOutput
import mni.thm.de.webmailclient.mail.Mail
import mni.thm.de.webmailclient.mail.MailStatus
import java.time.Instant
import java.util.UUID

data class MailOutput(

    @field:Schema(
        description = "Unique mail identifier"
    )
    val id: UUID,

    @field:Schema(
        description = "Owner of the mail"
    )
    val ownerId: UUID,

    @field:Schema(
        description = "Sender email address",
        example = "max.mustermann@example.com"
    )
    val sender: String,

    @field:Schema(
        description = "Recipient email address",
        example = "anna.schmidt@example.com"
    )
    val to: String,

    @field:Schema(
        description = "Carbon copy recipient"
    )
    val cc: String,

    @field:Schema(
        description = "Blind carbon copy recipient"
    )
    val bcc: String,

    @field:Schema(
        description = "Mail subject",
        example = "Project Update"
    )
    val subject: String,

    @field:Schema(
        description = "Mail content"
    )
    val body: String,

    @field:Schema(
        description = "Current mail status",
        example = "DRAFT"
    )
    val status: MailStatus,

    @field:Schema(
        description = "Creation timestamp"
    )
    val createdAt: Instant,

    @field:Schema(
        description = "Timestamp when the mail was sent"
    )
    val sentAt: Instant?,

    @field:Schema(
        description = "Mail attachments"
    )
    val attachments: List<AttachmentOutput>,
)

fun Mail.toOutput(): MailOutput {
    return MailOutput(
        id = id,
        ownerId = owner.id,
        sender = sender,
        to = to,
        cc = cc,
        bcc = bcc,
        subject = subject,
        body = body,
        status = status,
        createdAt = createdAt,
        sentAt = sentAt,
        attachments = attachments.map { it.toOutput() },
    )
}