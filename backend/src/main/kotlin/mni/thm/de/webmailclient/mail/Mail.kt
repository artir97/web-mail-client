package mni.thm.de.webmailclient.mail

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import mni.thm.de.webmailclient.attachment.Attachment
import mni.thm.de.webmailclient.user.User
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "mails")
data class Mail(
    @Id
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "owner_id")
    val owner: User,
    val sender: String,

    @Column(name = "recipient")
    val to: String,

    @Column(name = "cc_recipient")
    val cc: String = "",

    @Column(name = "bcc_recipient")
    val bcc: String = "",

    val subject: String,

    @Column(length = 5000)
    val body: String,

    @Enumerated(EnumType.STRING)
    val status: MailStatus = MailStatus.DRAFT,

    val createdAt: Instant = Instant.now(),
    val sentAt: Instant? = null,

    @OneToMany(mappedBy = "mail")
    val attachments: List<Attachment> = emptyList(),
)