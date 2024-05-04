package kellmertrackbackend.socket

import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebSocketHandler : TextWebSocketHandler() {

    //val sessions = mutableListOf<WebSocketSession>()
    val sessions = mutableMapOf<Int, WebSocketSession>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("========================== SESSÃO ESTABELECIDA: $session")
        val dispositivo = session.handshakeHeaders.getFirst("dispositivo")?.toIntOrNull()
        println("================ dispositivo recebido: $dispositivo")
        if (dispositivo != null) {
            sessions[dispositivo] = session
        } else {
            session.close()
        }
        //sessions.add(session)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("Mensagem recebida: ${message.payload}")

        val responseMessage = TextMessage("Resposta do servidor para: ${message.payload}")
        session.sendMessage(responseMessage)
    }

    fun sendMessage(type: String, entregaId: Int?, dispositivo: Int) {
        val message = "$type;$entregaId"
        val session = sessions[dispositivo]
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
        val session = sessions[dispositivo]
        session?.sendMessage(TextMessage(message))
    }
}