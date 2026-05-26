package mni.thm.de.webmailclient.mail

import mni.thm.de.webmailclient.mail.dto.MailCreate
import mni.thm.de.webmailclient.mail.dto.MailOutput
import mni.thm.de.webmailclient.mail.dto.MailUpdate
import mni.thm.de.webmailclient.mail.dto.toOutput
import mni.thm.de.webmailclient.user.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.util.UUID

@Service
class MailService (
    private val mailRepository: MailRepository,
    private val userRepository: UserRepository
) {
    fun createDraft(userId: UUID, mailCreate: MailCreate): MailOutput {
        val user = userRepository.findById(userId)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User with id $userId not found"
            )

        val mail = mailCreate.toMail(ownerId = user.id)
        return mailRepository.save(mail).toOutput()
    }

    fun findAllByUserId(userId: UUID): Set<MailOutput> {
        if (userRepository.findById(userId) == null) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User with id $userId not found"
            )
        }
        return mailRepository.findAllByOwnerId(userId)
            .map { it.toOutput() }
            .toSet()
    }

    fun findById(userId: UUID, mailId: UUID): MailOutput {
        return findMailOfUser(userId, mailId).toOutput()
    }

    fun updateDraft(userId: UUID, mailId: UUID, mailUpdate: MailUpdate): MailOutput {
        val existingMail = findMailOfUser(userId, mailId)

        val updatedMail = existingMail.copy(
            sender = mailUpdate.sender,
            to = mailUpdate.to,
            cc = mailUpdate.cc,
            bcc = mailUpdate.bcc,
            subject = mailUpdate.subject,
            body = mailUpdate.body,
        )

        return mailRepository.updateById(mailId, updatedMail)!!.toOutput()
    }

    fun deleteById(userId: UUID, mailId: UUID) {
        findMailOfUser(userId, mailId)

        val deleted = mailRepository.deleteById(mailId)

        if (!deleted) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Mail with id $mailId could not be deleted"
            )
        }
    }

    fun sendDraft(userId: UUID, mailId: UUID): MailOutput {
        val mail = findMailOfUser(userId, mailId)

        if (mail.status != MailStatus.DRAFT) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Only draft mails can be sent"
            )
        }

        val sentMail = mail.copy(
            status = MailStatus.SENT,
            sentAt = Instant.now(),
        )

        return mailRepository.updateById(mailId, sentMail)!!.toOutput()
    }

    // helper functions

    private fun findMailOfUser(userId: UUID, mailId: UUID): Mail {
        if (userRepository.findById(userId) == null) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User with id $userId not found"
            )
        }

        val mail = mailRepository.findById(mailId)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Mail with id $mailId not found"
            )

        if (mail.ownerId != userId) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Mail does not belong to user $userId"
            )
        }

        return mail
    }
}