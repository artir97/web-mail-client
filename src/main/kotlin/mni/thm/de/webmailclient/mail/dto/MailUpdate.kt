package mni.thm.de.webmailclient.mail.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class MailUpdate(
    @field:Email(message = "Sender must be a valid email address")
    val sender: String,
    @field:NotEmpty(message = "At least one recipient is required")
    val to: List<@Email(message = "Recipient must be a valid email address") String>,

    val cc: List<@Email(message = "CC recipient must be a valid email address") String> = emptyList(),

    val bcc: List<@Email(message = "BCC recipient must be a valid email address") String> = emptyList(),

    @field:NotBlank(message = "Subject must not be blank")
    val subject: String,

    @field:NotBlank(message = "Message must not be blank")
    val body: String,
)
