package mni.thm.de.webmailclient.user

import jakarta.validation.Valid
import mni.thm.de.webmailclient.user.dto.UserCreate
import mni.thm.de.webmailclient.user.dto.UserOutput
import mni.thm.de.webmailclient.user.dto.toOutput
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(
        @Valid @RequestBody userCreate: UserCreate
    ) : UserOutput {
        val user = userService.create(userCreate.toUser())
        return user.toOutput()
    }

    @GetMapping
    fun getAllUsers(): List<UserOutput> {
        return userService.findAll().map { it.toOutput() }
    }

    @GetMapping("/{id}")
    fun getUserById(
        @PathVariable id: UUID
    ) : UserOutput? {
        return userService.findById(id)?.toOutput()
    }
}