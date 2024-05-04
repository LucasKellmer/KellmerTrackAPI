package kellmertrackbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.socket.config.annotation.EnableWebSocket

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableWebSocket
class KellmertrackbackendApplication

fun main(args: Array<String>) {
    runApplication<KellmertrackbackendApplication>(*args)
}