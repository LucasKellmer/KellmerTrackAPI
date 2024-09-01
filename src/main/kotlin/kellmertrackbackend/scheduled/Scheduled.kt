package kellmertrackbackend.scheduled

import jakarta.transaction.Transactional
import kellmertrackbackend.service.DispositivoService
import kellmertrackbackend.service.TrajetoDiarioService
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.Date

@Service
@EnableScheduling
class Scheduled(
    private val alvaraConstrucaoService: DispositivoService,
    private val trajetoDiarioService: TrajetoDiarioService
) {

    //@Scheduled(cron = "0 0 04 * * *")
    //@Scheduled(fixedDelay = 60000)
    fun comprimiTrajetosDiarios() {
        println("Comprimindo trajetos diarios")
        trajetoDiarioService.comprimirTrajetoDiario()
    }

    //@Scheduled(cron = "*/15 * 02-22 *  * *")
    /*@Scheduled(fixedDelay = 600000)
    fun baixaAlvaraContrucao(){
        println("CHAMANDO SCHEDULED no momento : ${Date()}")
        alvaraConstrucaoService.baixaAlvaraConstrucao()
    }*/

    /*@Scheduled(fixedDelay = 600000000)
    fun baixaAlvaraContrucao(){
        println("CHAMANDO função capcha no momento : ${Date()}")
        alvaraConstrucaoService.captcha()
    }*/
}