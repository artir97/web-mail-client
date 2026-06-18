package mni.thm.de.webmailclient.attachment.dto

import mni.thm.de.webmailclient.attachment.Attachment
import java.util.UUID

data class AttachmentOutput(
    val id: UUID,
    val fileName: String,
    val contentType: String,
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
