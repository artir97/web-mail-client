package mni.thm.de.webmailclient.user
import mni.thm.de.webmailclient.user.dto.UserUpdate
import org.springframework.stereotype.Service
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun create(user: User): User {
        if (userRepository.existsByEmailIgnoreCase(user.email)) {
            throw ResponseStatusException(
                HttpStatus.CONFLICT,
                "User with email ${user.email} already exists"
            )
        }

        val hashedPassword = passwordEncoder.encode(user.password)
            ?: throw IllegalStateException("Password hashing failed")

        val userWithHashedPassword = user.copy(
            password = hashedPassword
        )

        return userRepository.save(userWithHashedPassword)
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findById(id: UUID): User {
        return userRepository.findById(id)
            .orElseThrow {
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User with id $id not found"
                )
            }
    }

    fun updateById(id: UUID, userUpdate: UserUpdate): User {
        val existingUser = userRepository.findById(id).orElseThrow{
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User with id $id not found"
            )
        }

        val updatedUser = existingUser.copy(
            email = userUpdate.email.trim(),
            firstName = userUpdate.firstName.trim(),
            lastName = userUpdate.lastName.trim(),
        )

        return userRepository.save(updatedUser)
    }

    fun deleteById(id: UUID) {
        val existingUser = findById(id)
        userRepository.delete(existingUser)
    }
}