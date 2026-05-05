package mni.thm.de.webmailclient.mail

import mni.thm.de.webmailclient.mail.dto.MailCreate
import mni.thm.de.webmailclient.mail.dto.MailOutput
import mni.thm.de.webmailclient.mail.dto.toOutput
import mni.thm.de.webmailclient.user.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MailService (
    private val mailRepository: MailRepository,
    private val userRepository: UserRepository
) {
    fun createDraft(userId: UUID, mailCreate: MailCreate): MailOutput {
        val user = userRepository.findById(userId)
            ?: throw IllegalStateException("User with id $userId not found")

        val mail = mailCreate.toMail(ownerId = user.id)
        return mailRepository.save(mail).toOutput()
    }



    fun findAllByUserId(userId: UUID): Set<MailOutput> {
        if (userRepository.findById(userId) == null) {
            throw IllegalArgumentException("User with id $userId not found")
        }
        return mailRepository.findAllByOwnerId(userId)
            .map { it.toOutput() }
            .toSet()
    }


}