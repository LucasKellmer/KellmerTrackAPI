package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.MonitoramentoTrajetoDTO
import kellmertrackbackend.model.dto.TrajetoDTO
import kellmertrackbackend.model.entities.TrajetoEntity
import kellmertrackbackend.repository.DispositivoRepository
import kellmertrackbackend.repository.VeiculoRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class TrajetoMapper(
    private val veiculoRepository: VeiculoRepository,
    private val dispositivoRepository: DispositivoRepository,
) {

    fun fromDTOtoTrajetoEntity(trajeto: TrajetoDTO): TrajetoEntity {
        return TrajetoEntity(
            id = UUID.fromString(trajeto.id),
            dispositivo = dispositivoRepository.findByNumeroInterno(trajeto.dispositivo)!!,
            veiculo = veiculoRepository.findByIdentificacao(trajeto.veiculoId),
            momento = trajeto.momento!!,
            latitude = trajeto.latitude!!,
            longitude = trajeto.longitude!!,
            velocidade = trajeto.velocidade!!,
        )
    }

    fun fromEntityToMonitoramentoTrajetoDTO(trajeto : TrajetoEntity?) : MonitoramentoTrajetoDTO?{
        val dispositivo = dispositivoRepository.findByNumeroInterno(trajeto!!.dispositivo.numeroInterno)
        return MonitoramentoTrajetoDTO(
            id = trajeto.id.toString(),
            dispositivo = trajeto.dispositivo.numeroInterno,
            veiculoId = trajeto.veiculo!!.identificacao,
            motorista = dispositivo?.motorista?.nome,
            momento = trajeto.momento,
            latitude = trajeto.latitude,
            longitude = trajeto.longitude,
            velocidade = trajeto.velocidade
        )
    }
}