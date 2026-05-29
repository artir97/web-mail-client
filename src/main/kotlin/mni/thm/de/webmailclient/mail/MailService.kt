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
class MailService(
    private val mailRepository: MailRepository,
    private val userRepository: UserRepository
) {
    fun createDraft(userId: UUID, mailCreate: MailCreate): MailOutput {
        val owner = userRepository.findById(userId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        val mail = mailCreate.toMail(owner)
        return mailRepository.save(mail).toOutput()
    }

    fun findAllByUserId(userId: UUID): Set<MailOutput> {
        ensureUserExists(userId)

        return mailRepository.findAllByOwner_Id(userId)
            .map { it.toOutput() }
            .toSet()
    }

    fun findById(userId: UUID, mailId: UUID): MailOutput {
        return findMailOfUser(userId, mailId).toOutput()
    }

    fun updateDraft(userId: UUID, mailId: UUID, mailUpdate: MailUpdate): MailOutput {
        val existingMail = findMailOfUser(userId, mailId)

        val updatedMail = existingMail.copy(
            sender = mailUpdate.sender.trim(),
            to = mailUpdate.to.trim(),
            cc = mailUpdate.cc.trim(),
            bcc = mailUpdate.bcc.trim(),
            subject = mailUpdate.subject.trim(),
            body = mailUpdate.body,
        )

        return mailRepository.save(updatedMail).toOutput()
    }

    fun deleteById(userId: UUID, mailId: UUID) {
        val mail = findMailOfUser(userId, mailId)
        mailRepository.delete(mail)
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

        return mailRepository.save(sentMail).toOutput()
    }

    private fun ensureUserExists(userId: UUID) {
        if (!userRepository.existsById(userId)) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User with id $userId not found"
            )
        }
    }

    private fun findMailOfUser(userId: UUID, mailId: UUID): Mail {
        ensureUserExists(userId)

        val mail = mailRepository.findById(mailId)
            .orElseThrow {
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Mail with id $mailId not found"
                )
            }

        if (mail.owner.id != userId) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Mail does not belong to user $userId"
            )
        }

        return mail
    }
}