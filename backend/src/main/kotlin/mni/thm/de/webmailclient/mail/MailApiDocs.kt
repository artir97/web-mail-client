package mni.thm.de.webmailclient.mail

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse

// TODO prof fragen ob weiter auslagern oder net
/**
 * zum Beispiel:
 * @Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
 * @Retention(AnnotationRetention.RUNTIME)
 * @ApiResponse(
 *     responseCode = "403",
 *     description = "Access to another user's mailbox is forbidden."
 * )
 * annotation class MailAccessForbiddenResponse
 */


// CREATE DRAFT
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    operationId = "createDraft",
    summary = "Create draft",
    description = "Creates a new draft mail for the user."
)
@ApiResponse(
    responseCode = "201",
    description = "Draft created successfully."
)
@ApiResponse(
    responseCode = "400",
    description = "Invalid mail data."
)
@ApiResponse(
    responseCode = "403",
    description = "Access to another user's mailbox is forbidden."
)
annotation class CreateDraftDocumentation


// GET ALL MAILS
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    operationId = "findAllByUserId",
    summary = "Get all mails",
    description = "Returns all mails belonging to the user."
)
@ApiResponse(
    responseCode = "200",
    description = "Mails returned successfully."
)
@ApiResponse(
    responseCode = "403",
    description = "Access to another user's mailbox is forbidden."
)
annotation class FindAllMailsDocumentation


// GET MAIL BY ID
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    operationId = "findById",
    summary = "Get mail by id",
    description = "Returns a single mail."
)
@ApiResponse(
    responseCode = "200",
    description = "Mail returned successfully."
)
@ApiResponse(
    responseCode = "403",
    description = "Access to another user's mailbox is forbidden."
)
@ApiResponse(
    responseCode = "404",
    description = "Mail not found."
)
annotation class FindMailByIdDocumentation


// UPDATE DRAFT
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    operationId = "updateDraft",
    summary = "Update draft",
    description = "Updates an existing draft mail."
)
@ApiResponse(
    responseCode = "200",
    description = "Draft updated successfully."
)
@ApiResponse(
    responseCode = "400",
    description = "Invalid mail data."
)
@ApiResponse(
    responseCode = "403",
    description = "Access to another user's mailbox is forbidden."
)
@ApiResponse(
    responseCode = "404",
    description = "Mail not found."
)
annotation class UpdateDraftDocumentation


// DELETE MAIL
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    operationId = "deleteById",
    summary = "Delete mail",
    description = "Deletes a mail by id."
)
@ApiResponse(
    responseCode = "204",
    description = "Mail deleted successfully."
)
@ApiResponse(
    responseCode = "403",
    description = "Access to another user's mailbox is forbidden."
)
@ApiResponse(
    responseCode = "404",
    description = "Mail not found."
)
annotation class DeleteMailDocumentation


// SEND DRAFT
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    operationId = "sendDraft",
    summary = "Send draft",
    description = "Sends a draft mail and changes its status to SENT."
)
@ApiResponse(
    responseCode = "200",
    description = "Mail sent successfully."
)
@ApiResponse(
    responseCode = "403",
    description = "Access to another user's mailbox is forbidden."
)
@ApiResponse(
    responseCode = "404",
    description = "Mail not found."
)
annotation class SendDraftDocumentation