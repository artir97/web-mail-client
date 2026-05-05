package mni.thm.de.webmailclient.mail

import jakarta.validation.Valid
import mni.thm.de.webmailclient.mail.dto.MailCreate
import mni.thm.de.webmailclient.mail.dto.MailOutput
import org.springframework.http.HttpStatus
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
}