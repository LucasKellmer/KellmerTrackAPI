package kellmertrackbackend.scheduled

import jakarta.transaction.Transactional
import kellmertrackbackend.service.DispositivoService
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.Date

@Service
@EnableScheduling
class Scheduled(
    private val alvaraConstrucaoService: DispositivoService
) {

    //@Scheduled(cron = "*/15 * 02-22 *  * *")
    /*@Scheduled(fixedDelay = 600000)
    fun baixaAlvaraContrucao(){
        println("CHAMANDO SCHEDULED no momento : ${Date()}")
        alvaraConstrucaoService.baixaAlvaraConstrucao()
    }*/

}