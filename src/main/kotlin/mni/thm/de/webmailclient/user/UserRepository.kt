package mni.thm.de.webmailclient.user

import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    private val userStorage = mutableSetOf<User>()

    fun userExistsByEmail(email: String): Boolean {
        return userStorage.any {
            it.email.equals(email, ignoreCase = true)
        }
    }

    fun save(user: User): User {
        userStorage.add(user)
        return user
    }

    fun findAll(): List<User> {
        return userStorage.toList()
    }

    fun findById(id: java.util.UUID): User? {
        return userStorage.find { it.id == id }
    }

    fun update(id: java.util.UUID, updatedUser: User): User? {
        val existingUser = findById(id) ?: return null

        userStorage.remove(existingUser)
        userStorage.add(updatedUser)

        return updatedUser
    }
}