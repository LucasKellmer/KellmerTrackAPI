package kellmertrackbackend.socket

import org.springframework.web.socket.TextMessage

interface MonitoramentoSocketMsg {
    fun enviaMensagemWebClient(message : SocketMessage)
}