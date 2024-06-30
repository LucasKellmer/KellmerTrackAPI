package kellmertrackbackend.scheduled

import kellmertrackbackend.firebase.FirebaseDatabase
import kellmertrackbackend.repository.DispositivoRepository
import kellmertrackbackend.repository.EntregasConsumoRepository
import kellmertrackbackend.socket.MonitoramentoSocket
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
@EnableScheduling
class KellmertrackScheduled (
    private val firebase : FirebaseDatabase,
    private val entregasConsumo : EntregasConsumoRepository,
    private val dispositivoRepository: DispositivoRepository,
    private val monitoramentoSocket: MonitoramentoSocket
) {
    @PostConstruct
    private fun init() {
        println("Iniciando Listeners")
        firebase.fechaListeners()
        firebase.FirebaseDatabase()
        firebase.criaListenerMonitoramento()
    }

    @Scheduled(cron = "0 00 1 * * *")
    fun fechaListners() {
        firebase.fechaListeners()
    }

    @Scheduled(fixedDelay = 30000)
    fun entregasNaoConsumidas(){
        val entregasNaoConsumidas = entregasConsumo.findEntregasNaoConsumidas()
        if(entregasNaoConsumidas.isNotEmpty()){
            entregasNaoConsumidas.forEach { entrega ->
                println("Entregas não consumidas:")
                println(entrega)
                if (entrega != null) {
                    val dispositivo = dispositivoRepository.findByVeiculoIdentificacao(entrega.veiculo?.identificacao)
                        if (dispositivo != null){
                            monitoramentoSocket.entregasPendentes.add(entrega)
                            monitoramentoSocket.novoCarregamento(dispositivo.numeroInterno.toInt())
                        }
                }
            }
        }
    }
/*
    //@Scheduled(cron = "0 10 1 * * *")
    @Scheduled(fixedRate = 300000)
    fun atualizaTodasLocalizacoes(){
        println("CRIANDO LISTENER TODAS LOCALIZAÇÕES")
        firebase.atualizaTodasLocalizacoes()
    }

    @Scheduled(cron = "0 10 1 * * *")
    fun atualizaTodasRotacoes(){
        println("CRIANDO LISTENER TODAS ROTACOES")
        firebase.atualizaTodasRotacoes()
    }
*/
    @Scheduled(fixedDelay = 600000)
    fun atualizaTodosRegistroFirebase(){
        println("GRAVANDO TODOS OS EVENTOS")
        firebase.atualizaTodosEventos()
        println("GRAVANDO TODAS AS ROTACOES")
        firebase.atualizaTodasRotacoes()
        println("GRAVANDO TODAS AS LOCALIZAÇÕES")
        firebase.atualizaTodasLocalizacoes()
        println("GRAVANDO TODAS AS ENTREGAS")
        firebase.atualizaTodasEntregas()
    }

    @Scheduled(cron = "0 00 05 * * *")
    fun iniciaMonitoramento() {
        println("INICIA MONITORAMENTO----------------")
        firebase.fechaListeners()
        firebase.FirebaseDatabase()
        firebase.criaListenerMonitoramento()
    }
}