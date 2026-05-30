package mni.thm.de.webmailclient.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface UserRepository: JpaRepository<User, UUID> {
    fun existsByEmailIgnoreCase(email: String): Boolean
    fun findByEmailIgnoreCase(email: String): Optional<User>
}