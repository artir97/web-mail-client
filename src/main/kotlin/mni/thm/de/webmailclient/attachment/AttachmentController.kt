package mni.thm.de.webmailclient.attachment

import mni.thm.de.webmailclient.attachment.dto.AttachmentOutput
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@RestController
@RequestMapping("/users/{userId}/mails/{mailId}/attachments")
class AttachmentController (
    private val attachmentService: AttachmentService,
) {
    fun uploadAttachment(
        @PathVariable userId: UUID,
        @PathVariable mailId: UUID,
        @RequestParam("file") file: MultipartFile,
    ): AttachmentOutput {
        return attachmentService.uploadAttachment(
            mailId = mailId,
            file = file,
        )
    }
}