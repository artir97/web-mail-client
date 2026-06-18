package mni.thm.de.webmailclient.attachment

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.JoinColumn
import mni.thm.de.webmailclient.mail.Mail
import java.util.UUID

@Entity
@Table(name = "attachments")
class Attachment(
    @Id
    val id: UUID = UUID.randomUUID(),
    val fileName: String,
    val contentType: String,
    val size: Long,
    val storagePath: String,

    @ManyToOne
    @JoinColumn(name = "mail_id")
    val mail: Mail,
) {
    init {
        require(fileName.isNotBlank()) { "fileName must not be blank" }
        require (contentType.isNotBlank()) { "contentType must not be blank" }
        require(size > 0) { "Size must be greater than zero" }
        require(storagePath.isNotBlank()) { "storagePath must not be blank" }
    }
}