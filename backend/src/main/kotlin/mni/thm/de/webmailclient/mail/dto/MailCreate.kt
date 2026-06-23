package mni.thm.de.webmailclient.mail.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import mni.thm.de.webmailclient.mail.Mail
import mni.thm.de.webmailclient.user.User

data class MailCreate(
    @field:Schema(
        description = "Sender email address",
        example = "max.mustermann@example.com"
    )
    @field:Email(message = "Sender must be a valid email address")
    @field:NotBlank(message = "Sender must not be blank")
    val sender: String,

    @field:Schema(
        description = "Recipient email address",
        example = "anna.schmidt@example.com"
    )
    @field:Email(message = "Recipient must be a valid email address")
    @field:NotBlank(message = "Recipient is required")
    val to: String,

    @field:Schema(
        description = "Carbon copy recipient",
        example = "team@example.com"
    )
    @field:Email(message = "CC recipient must be a valid email address")
    val cc: String = "",

    @field:Schema(
        description = "Blind carbon copy recipient",
        example = "manager@example.com"
    )
    @field:Email(message = "BCC recipient must be a valid email address")
    val bcc: String = "",

    @field:Schema(
        description = "Blind carbon copy recipient",
        example = "manager@example.com"
    )
    @field:NotBlank(message = "Subject must not be blank")
    val subject: String,

    @field:Schema(
        description = "Mail content",
        example = "Hello, here is the current project status."
    )
    @field:NotBlank(message = "Message must not be blank")
    val body: String,
) {
    fun toMail(owner: User): Mail {
        return Mail(
            owner = owner,
            sender = sender.trim(),
            to = to.trim(),
            cc = cc.trim(),
            bcc = bcc.trim(),
            subject = subject.trim(),
            body = body,
        )
    }
}