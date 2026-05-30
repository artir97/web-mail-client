package mni.thm.de.webmailclient.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import mni.thm.de.webmailclient.user.User
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService {

    private val secret = "this-is-a-demo-secret-key-for-jwt-authentication-please-change"
    private val expirationMs = 1000 * 60 * 60L

    private fun signingKey(): SecretKey {
        return Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun generateToken(user: User): String {
        val now = Date()
        val expiration = Date(now.time + expirationMs)

        return Jwts.builder()
            .subject(user.id.toString())
            .claim("email", user.email)
            .issuedAt(now)
            .expiration(expiration)
            .signWith(signingKey())
            .compact()
    }
}