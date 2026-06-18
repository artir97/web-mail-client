package mni.thm.de.webmailclient.attachment

import mni.thm.de.webmailclient.attachment.dto.AttachmentOutput
import mni.thm.de.webmailclient.attachment.dto.toOutput
import mni.thm.de.webmailclient.mail.MailRepository
import mni.thm.de.webmailclient.storage.FileStorageService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
class AttachmentService (
    private val attachmentRepository: AttachmentRepository,
    private val mailRepository: MailRepository,
    private val fileStorageService: FileStorageService
) {
    fun uploadAttachment(
        mailId: UUID,
        file: MultipartFile,
    ): AttachmentOutput {
        val mail = mailRepository.findById(mailId)
            .orElseThrow {
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Mail not found"
                )
            }

        val storagePath = fileStorageService.save(file)

        val attachment = Attachment(
            fileName = file.originalFilename ?: "attachment",
            contentType = file.contentType ?: "application/octet-stream",
            size = file.size,
            storagePath = storagePath,
            mail = mail
        )

        return attachmentRepository
            .save(attachment)
            .toOutput()

    }
}