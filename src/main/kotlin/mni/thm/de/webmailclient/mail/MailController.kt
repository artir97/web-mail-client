package mni.thm.de.webmailclient.mail

import jakarta.validation.Valid
import mni.thm.de.webmailclient.mail.dto.MailCreate
import mni.thm.de.webmailclient.mail.dto.MailOutput
import mni.thm.de.webmailclient.mail.dto.MailUpdate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.security.core.Authentication
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
        @Valid @RequestBody mailCreate: MailCreate,
        authentication: Authentication
    ): MailOutput {
        val authenticatedUserId = authentication.principal as UUID
        return mailService.createDraft(
            userId,
            authenticatedUserId,
            mailCreate
        )
    }

    @GetMapping
    fun findAllByUserId(
        @PathVariable userId: UUID,
        authentication: Authentication
    ): Set<MailOutput> {
        val authenticatedUserId = authentication.principal as UUID
        return mailService.findAllByUserId(
            userId,
            authenticatedUserId
        )
    }

    @GetMapping("/{mailId}")
    fun findById(
        @PathVariable userId: UUID,
        @PathVariable mailId: UUID,
        authentication: Authentication
    ): MailOutput {
        val authenticatedUserId = authentication.principal as UUID
        return mailService.findById(
            userId,
            authenticatedUserId,
            mailId
        )
    }

    @PutMapping("/{mailId}")
    fun updateDraft(
        @PathVariable userId: UUID,
        @PathVariable mailId: UUID,
        @Valid @RequestBody mailUpdate: MailUpdate,
        authentication: Authentication
    ): MailOutput {
        val authenticatedUserId =
            authentication.principal as UUID

        return mailService.updateDraft(
            userId,
            authenticatedUserId,
            mailId,
            mailUpdate
        )
    }

    @DeleteMapping("/{mailId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(
        @PathVariable userId: UUID,
        @PathVariable mailId: UUID,
        authentication: Authentication
    ) {
        val authenticatedUserId = authentication.principal as UUID

        mailService.deleteById(
            userId,
            authenticatedUserId,
            mailId
        )
    }

    @PostMapping("/{mailId}/send")
    fun sendDraft(
        @PathVariable userId: UUID,
        @PathVariable mailId: UUID,
        authentication: Authentication
    ): ResponseEntity<MailOutput> {
        val authenticatedUserId = authentication.principal as UUID

        val sentMail = mailService.sendDraft(
            userId,
            authenticatedUserId,
            mailId
        )

        return ResponseEntity.ok(sentMail)
    }
}