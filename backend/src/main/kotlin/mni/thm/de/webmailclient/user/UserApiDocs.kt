package mni.thm.de.webmailclient.user

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
//TODO prof fragen ob weiter aulsagern oder net
/**
 * zum Beispiel:
 * @Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
 * @Retention(AnnotationRetention.RUNTIME)
 * @ApiResponse(
 *     responseCode = "400",
 *     description = "Invalid user data."
 * )
 * annotation class InvalidUserDataResponse
 */


// CREATE USER
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    operationId = "createUser",
    summary = "Create new user",
    description = "Create a new user account."
)
@ApiResponse(
    responseCode = "201",
    description = "User created successfully."
)
@ApiResponse(
    responseCode = "400",
    description = "Invalid user data."
)
@ApiResponse(
    responseCode = "409",
    description = "Email already exists."
)
annotation class CreateUserDocumentation


// GET ALL USER
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    operationId = "getAllUsers",
    summary = "Get all users",
    description = "Returns all registered users."
)
@ApiResponse(
    responseCode = "200",
    description = "Users returned successfully."
)
annotation class GetAllUsersDocumentation


// GET USER BY ID
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    operationId = "getUserById",
    summary = "Get user by id",
    description = "Returns a single user by its id."
)
@ApiResponse(
    responseCode = "200",
    description = "User returned successfully."
)
@ApiResponse(
    responseCode = "404",
    description = "User not found."
)
annotation class GetUserByIdDocumentation


// GET UPDATE USER BY ID
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    operationId = "updateUserById",
    summary = "Update user by id",
    description = "Updates the email, first name and last name of an existing user."
)
@ApiResponse(
    responseCode = "200",
    description = "User updated successfully."
)
@ApiResponse(
    responseCode = "400",
    description = "Invalid user data."
)
@ApiResponse(
    responseCode = "404",
    description = "User not found."
)
@ApiResponse(
    responseCode = "409",
    description = "Email already exists."
)
annotation class UpdateUserByIdDocumentation


// DELETE USER BY ID
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    operationId = "deleteUserById",
    summary = "Delete user by id",
    description = "Deletes an existing user by its id."
)
@ApiResponse(
    responseCode = "204",
    description = "User deleted successfully."
)
@ApiResponse(
    responseCode = "404",
    description = "User not found."
)
annotation class DeleteUserByIdDocumentation