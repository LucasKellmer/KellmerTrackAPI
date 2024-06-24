package kellmertrackbackend.socket

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(
    private val webSocketHandler: WebSocketHandler,
) : WebSocketConfigurer {

    /*@Bean
    fun webSocketHandler(): WebSocketHandler {
        return WebSocketHandler()
    }*/

    @Bean
    fun monitoramentoSocket(webSocketHandler: WebSocketHandler): MonitoramentoSocket {
        return MonitoramentoSocket(webSocketHandler)
    }

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(webSocketHandler, "/websocket")
            .setAllowedOrigins("*");
    }
}