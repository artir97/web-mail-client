package mni.thm.de.webmailclient.mail

import jakarta.validation.Valid
import mni.thm.de.webmailclient.mail.dto.MailCreate
import mni.thm.de.webmailclient.mail.dto.MailOutput
import mni.thm.de.webmailclient.mail.dto.MailUpdate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/users/{userId}/mails")
class MailController(
    private val mailService: MailService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDraft(
        @PathVariable userId: UUID,
        @Valid @RequestBody mailCreate: MailCreate
    ): MailOutput {
        return mailService.createDraft(userId, mailCreate)
    }

    @GetMapping
    fun findAllByUserId(
        @PathVariable userId: UUID
    ): Set<MailOutput> {
        return mailService.findAllByUserId(userId)
    }

    @GetMapping("/{mailId}")
    fun findById(
        @PathVariable userId: UUID,
        @PathVariable mailId: UUID
    ): MailOutput {
        return mailService.findById(userId, mailId)
    }

    @PutMapping("/{mailId}")
    fun updateDraft(
        @PathVariable userId: UUID,
        @PathVariable mailId: UUID,
        @Valid @RequestBody mailUpdate: MailUpdate
    ): MailOutput {
        return mailService.updateDraft(userId, mailId, mailUpdate)
    }

    @DeleteMapping("/{mailId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(
        @PathVariable userId: UUID,
        @PathVariable mailId: UUID
    ) {
        mailService.deleteById(userId, mailId)
    }

    @PostMapping("/{mailId}/send")
    fun sendDraft(
        @PathVariable userId: UUID,
        @PathVariable mailId: UUID,
    ): ResponseEntity<MailOutput> {
        val sentMail = mailService.sendDraft(userId, mailId)
        return ResponseEntity.ok(sentMail)
    }
}