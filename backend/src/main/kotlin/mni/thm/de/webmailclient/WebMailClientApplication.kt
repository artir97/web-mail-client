package mni.thm.de.webmailclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebMailClientApplication

fun main(args: Array<String>) {
    runApplication<WebMailClientApplication>(*args)
}
