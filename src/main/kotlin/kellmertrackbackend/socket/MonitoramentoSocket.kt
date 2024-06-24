package kellmertrackbackend.socket

import kellmertrackbackend.model.entities.EntregaConsumoEntity
import org.springframework.stereotype.Component

@Component
class MonitoramentoSocket(private val webSocketHandler: WebSocketHandler) {

    final val entregasPendentes : MutableList<EntregaConsumoEntity> = mutableListOf()

    init {
        println("Entregas Pendentes: ${entregasPendentes.size}")
    }

    fun novoCarregamento(dispositivo: Int){
        println("Novo carregamento dispositivo: $dispositivo")
        val entregasParaRemover = mutableListOf<EntregaConsumoEntity>()
        if (!entregasPendentes.isNullOrEmpty()){
            entregasPendentes.forEach { entregaPendente ->
                enviarMensagem("CARREGAMENTO", entregaPendente.entrega?.id, dispositivo)
                entregasParaRemover.add(entregaPendente)
            }
        }
        entregasPendentes.removeAll(entregasParaRemover)
    }

    fun enviarMensagem(tipo: String, entregaId: Int?, dispositivo : Int) {
        println("webSocketHandler.sessions -> mobileSessions: ${webSocketHandler.mobileSessions}")
        println("webSocketHandler.session: -> webSessions: ${webSocketHandler.webSessions}")
        webSocketHandler.sendMessage(tipo, entregaId, dispositivo)
    }
}