package mni.thm.de.webmailclient.config

import mni.thm.de.webmailclient.auth.JwtAuthFilter
import mni.thm.de.webmailclient.auth.JwtService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtService: JwtService,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity, jwtAuthFilter: JwtAuthFilter): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .headers {
                it.frameOptions { frameOptions ->
                    frameOptions.disable()
                }
            }
            .authorizeHttpRequests {
                it.requestMatchers("/login").permitAll()
                it.requestMatchers(org.springframework.http.HttpMethod.POST, "/users").permitAll()
                it.requestMatchers("/h2-console/**").permitAll()
                it.anyRequest().authenticated()
            }
            .addFilterBefore(
                jwtAuthFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )
            .build()
    }
}