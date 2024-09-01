package kellmertrackbackend.service

import jakarta.transaction.Transactional
import kellmertrackbackend.model.entities.TrajetoDiarioEntity
import kellmertrackbackend.repository.TrajetoDiarioRepository
import kellmertrackbackend.repository.TrajetoRepository
import kellmertrackbackend.service.utils.TrajetoUtil
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Service
class TrajetoDiarioService(
    private val trajetoDiarioRepository: TrajetoDiarioRepository,
    private val trajetoRepository: TrajetoRepository
) {

    @Transactional
    fun comprimirTrajetoDiario(){
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val timeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        for (i in 1..8){
            val dateSql: Date = java.sql.Date.valueOf(LocalDate.now().minusDays(i.toLong()))

            trajetoRepository.buscaVeiculosTrajeto(dateSql).forEach { veiculo ->

                val trajetoUtil = TrajetoUtil()

                val trajetoDiario = trajetoRepository.findByVeiculoIdentificacaoAndMomentoBetweenOrderByMomento(
                    veiculo,
                    timeFormat.parse(dateFormat.format(dateSql.time).plus(" 00:00:00")),
                    timeFormat.parse(dateFormat.format(dateSql.time).plus(" 24:00:00"))
                )

                if (trajetoDiario != null) {
                    val trajeto = trajetoUtil.comprimirTrajeto(trajetoDiario)

                    trajetoDiarioRepository.save(
                        TrajetoDiarioEntity(
                            veiculo = veiculo,
                            data = dateFormat.parse(dateFormat.format(dateSql.time)),
                            trajetoZip = trajeto!!
                        )
                    )
                    /*trajetoRepository.deleteAllByVeiculoIdentificacao_IdentificacaoAndMomentoBetween(
                        veiculo,
                        timeFormat.parse(dateFormat.format(dateSql.time).plus(" 00:00:00")),
                        timeFormat.parse(dateFormat.format(dateSql.time).plus(" 24:00:00"))
                    )*/
                }
            }
        }
    }
}