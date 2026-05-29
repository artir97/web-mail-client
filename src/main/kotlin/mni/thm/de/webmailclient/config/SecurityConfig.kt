package mni.thm.de.webmailclient.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .headers {
                it.frameOptions { frameOptions ->
                    frameOptions.disable()
                }
            }
            .authorizeHttpRequests {
                it.anyRequest().permitAll()
            }
            .build()
    }
}