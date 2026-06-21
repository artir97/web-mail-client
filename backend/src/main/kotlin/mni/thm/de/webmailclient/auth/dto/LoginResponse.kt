package mni.thm.de.webmailclient.auth.dto

import io.swagger.v3.oas.annotations.media.Schema

data class LoginResponse(

    @field:Schema(
        description = "JWT access token",
        example = "eyJhbGciOiJIUzI1NiJ9..."
    )
    val token: String,
)