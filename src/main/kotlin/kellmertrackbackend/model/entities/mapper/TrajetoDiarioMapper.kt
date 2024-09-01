package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.TrajetoDiarioDTO
import kellmertrackbackend.model.entities.TrajetoDiarioEntity
import kellmertrackbackend.repository.RotacaoRepository
import kellmertrackbackend.service.utils.TrajetoUtil
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
class TrajetoDiarioMapper(
    private val rotacaoRepository: RotacaoRepository
) {

    fun fromEntityToDTO(trajeto : TrajetoDiarioEntity) : TrajetoDiarioDTO {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val timeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val rotacoes = rotacaoRepository.buscaRotacaoByData(
            trajeto.data,
            timeFormat.parse(dateFormat.format(trajeto.data).plus(" 24:00:00")),
            trajeto.veiculo)
        val trajetoUtil = TrajetoUtil()
        return TrajetoDiarioDTO(
            id = trajeto.id!!,
            data = trajeto.data,
            veiculo = trajeto.veiculo,
            trajeto = trajetoUtil.descomprimirTrajeto(trajeto.trajetoZip),
            rotacoes =  rotacoes
        )
    }
}