package mni.thm.de.webmailclient.mail

import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class MailRepository {
    private val mails = mutableSetOf<Mail>()
    fun save(mail: Mail): Mail {
        mails.add(mail)
        return mail
    }

    fun findAll(): Set<Mail> {
        return mails
    }

    fun findById(id: UUID): Mail? {
        return mails.find { it.id == id }
    }

    fun findAllByOwnerId(ownerId: UUID): Set<Mail> {
        return mails.filter { it.ownerId == ownerId }.toSet()
    }

    fun deleteById(id: UUID): Boolean {
        return mails.removeIf { it.id == id }
    }

}