package kellmertrackbackend.socket

import kellmertrackbackend.firebase.FirebaseDatabase
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.util.function.Function
import java.util.stream.Collectors
import javax.annotation.PostConstruct

@Component
class WebSocketHandler (
    private val firebaseDatabase: FirebaseDatabase
): TextWebSocketHandler(), MonitoramentoSocketMsg {

    //val sessions = mutableListOf<WebSocketSession>()
    val mobileSessions = mutableMapOf<Int, WebSocketSession>()
    val webSessions = mutableMapOf<Int, WebSocketSession>()

    @PostConstruct
    private fun init() {
        firebaseDatabase.FirebaseDatabase()
        firebaseDatabase.setSocketMsg(this)
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("========================== SESSÃO ESTABELECIDA: $session")

        // Obtendo os parâmetros de consulta
        val uri = session.uri
        val queryParams = uri?.query?.split("&")?.associate {
            val (key, value) = it.split("=")
            key to URLDecoder.decode(value, StandardCharsets.UTF_8.toString())
        }

        val user = queryParams?.get("user")?.toIntOrNull()
        val dispositivo = queryParams?.get("dispositivo")?.toIntOrNull()
        println("================ user recebido: $user")
        println("=================== dispositivo recebido: $dispositivo")
        if(user != null){
            webSessions[user] = session
        }

        if(dispositivo != null){
            mobileSessions[dispositivo] = session
        }

        /*val dispositivo = session.handshakeHeaders.getFirst("dispositivo")?.toIntOrNull()
        println("================ dispositivo recebido: $dispositivo")
        if (dispositivo != null) {
            MobileSessions[dispositivo] = session
        } else {
            session.close()
        }
        sessions.add(session)*/
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("========================== SESSÃO FECHADA: $session")

        // Removendo a sessão dos mapas mobileSessions e webSessions
        mobileSessions.entries.removeIf { it.value.id == session.id }
        webSessions.entries.removeIf { it.value.id == session.id }
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("Mensagem recebida: ${message.payload}")

        val responseMessage = TextMessage(message.payload)
        session.sendMessage(responseMessage)
    }

    fun sendMessage(type: String, entregaId: Int?, dispositivo: Int) {
        val message = "$type;$entregaId"
        val session = mobileSessions[dispositivo]
        session?.let {
            if (it.isOpen){
                it.sendMessage(TextMessage(message))
            }
        }

        /*if (!sessions.isNullOrEmpty()){
            val session = sessions[dispositivo]
            session?.sendMessage(TextMessage(message))
            sessions.forEach { session ->
                if (session.isOpen) {
                    session.sendMessage(TextMessage(message))
                }
            }
        }*/
    }

    fun sendMessageToDevice(dispositivo: Int, type: String, text: String?) {
        val message = "$type;$text"
        val session = mobileSessions[dispositivo]
        session?.sendMessage(TextMessage(message))
    }

    override fun enviaMensagemWebClient(message: SocketMessage) {
        println("Enviando mensagem para web")
        println("webSessions: ${webSessions.values}")
        this.webSessions.values.stream().filter { x: WebSocketSession ->
            x.isOpen
        }.forEach { x: WebSocketSession ->
            try {
                x.sendMessage(TextMessage(message.toString()))
            } catch (e: java.lang.Exception) {
                println("Não foi possível enviar a mensagem: ${e.message}")
            }
        }
    }
}