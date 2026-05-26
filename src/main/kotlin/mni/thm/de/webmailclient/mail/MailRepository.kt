package mni.thm.de.webmailclient.mail

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MailRepository : JpaRepository<Mail, UUID> {
    fun findAllByOwnerId(ownerId: UUID): List<Mail>
}