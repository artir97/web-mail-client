package mni.thm.de.webmailclient.user

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import mni.thm.de.webmailclient.user.dto.UserCreate
import mni.thm.de.webmailclient.user.dto.UserOutput
import mni.thm.de.webmailclient.user.dto.UserUpdate
import mni.thm.de.webmailclient.user.dto.toOutput
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(
    name = "User",
    description = "Manage user accounts."
)
@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @CreateUserDocumentation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(
        @Valid @RequestBody userCreate: UserCreate
    ) : UserOutput {
        val user = userService.create(userCreate.toUser())
        return user.toOutput()
    }

    @GetAllUsersDocumentation
    @GetMapping
    fun getAllUsers(): List<UserOutput> {
        return userService.findAll().map { it.toOutput() }
    }

    @GetUserByIdDocumentation
    @GetMapping("/{id}")
    fun getUserById(
        @PathVariable id: UUID
    ) : UserOutput {
        return userService.findById(id).toOutput()
    }

    @UpdateUserByIdDocumentation
    @PutMapping("/{id}")
    fun updateUserById(
        @PathVariable id: UUID,
        @Valid @RequestBody userUpdate: UserUpdate,
    ): UserOutput {
        return userService.updateById(id, userUpdate).toOutput()
    }

    @DeleteUserByIdDocumentation
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUserById(
        @PathVariable id: UUID
    ) {
        userService.deleteById(id)
    }
}