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
            dispositivoId = dispositivoRepository.findDispositivoById(trajeto.dispositivoId!!),
            veiculos = veiculoRepository.findByIdentificacao(trajeto.veiculoId),
            momento = trajeto.momento!!,
            latitude = trajeto.latitude!!,
            longitude = trajeto.longitude!!,
            velocidade = trajeto.velocidade!!,
        )
    }

    fun fromEntityToMonitoramentoTrajetoDTO(trajeto : TrajetoEntity?) : MonitoramentoTrajetoDTO?{
        val dispositivo = dispositivoRepository.findDispositivoById(trajeto!!.dispositivoId.id)
        return MonitoramentoTrajetoDTO(
            id = trajeto.id.toString(),
            dispositivoId = trajeto.dispositivoId.id,
            veiculoId = trajeto.veiculos!!.identificacao,
            motorista = dispositivo.motorista.nome,
            momento = trajeto.momento,
            latitude = trajeto.latitude,
            longitude = trajeto.longitude,
            velocidade = trajeto.velocidade
        )
    }
}