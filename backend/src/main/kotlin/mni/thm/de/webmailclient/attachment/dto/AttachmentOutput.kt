package mni.thm.de.webmailclient.attachment.dto

import io.swagger.v3.oas.annotations.media.Schema
import mni.thm.de.webmailclient.attachment.Attachment
import java.util.UUID

data class AttachmentOutput(

    @field:Schema(
        description = "Unique attachment identifier"
    )
    val id: UUID,

    @field:Schema(
        description = "Original file name",
        example = "invoice.pdf"
    )
    val fileName: String,

    @field:Schema(
        description = "Media type of the file",
        example = "application/pdf"
    )
    val contentType: String,

    @field:Schema(
        description = "File size in bytes",
        example = "55764"
    )
    val size: Long
)

fun Attachment.toOutput(): AttachmentOutput {
    return AttachmentOutput(
        id = id,
        fileName = fileName,
        contentType = contentType,
        size = size
    )
}
