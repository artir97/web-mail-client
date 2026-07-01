package mni.thm.de.webmailclient.attachment

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse

// UPLOAD ATTACHMENT
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Operation(
    operationId = "uploadAttachment",
    summary = "Upload attachment",
    description = "Uploads a file attachment for a mail."
)
@ApiResponse(
    responseCode = "201",
    description = "Attachment uploaded successfully."
)
@ApiResponse(
    responseCode = "400",
    description = "Invalid attachment data."
)
@ApiResponse(
    responseCode = "404",
    description = "Mail not found."
)
annotation class UploadAttachmentDocumentation