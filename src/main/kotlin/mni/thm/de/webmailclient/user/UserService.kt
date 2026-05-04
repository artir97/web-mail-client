package mni.thm.de.webmailclient.user
import mni.thm.de.webmailclient.user.dto.UserUpdate
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun create(user: User): User {
        if (userRepository.userExistsByEmail(user.email)) {
            throw IllegalArgumentException(
                "User with email ${user.email} already exists"
            )
        }
        return userRepository.save(user)
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findById(id: java.util.UUID): User? {
        return userRepository.findById(id)
    }

    fun updateById(id: java.util.UUID, userUpdate: UserUpdate): User? {
        val existingUser = userRepository.findById(id) ?: return null

        val updatedUser = existingUser.copy(
            email = userUpdate.email.trim(),
            firstName = userUpdate.firstName.trim(),
            lastName = userUpdate.lastName.trim(),
        )

        return userRepository.updateById(id, updatedUser)
    }

}