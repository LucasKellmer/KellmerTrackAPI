package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.EventoDTO
import kellmertrackbackend.model.entities.EventoEntity
import kellmertrackbackend.repository.ContratoRepository
import kellmertrackbackend.repository.EntregaRepository
import kellmertrackbackend.repository.ObraRepository
import kellmertrackbackend.repository.VeiculoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class EventoMapper(
    private val entregaRepository: EntregaRepository,
    private val veiculoRepository: VeiculoRepository,
    private val contratoRepository: ContratoRepository,
) {

    fun fromEventoDTOtoEventoEntity(evento : EventoDTO) : EventoEntity{
        return EventoEntity(
            id = evento.id,
            entregaId = entregaRepository.findByIdOrNull(evento.entregaId),
            contrato = contratoRepository.findByIdOrNull(evento.contrato),
            momento = evento.momento,
            latitude = evento.latitude,
            longitude = evento.longitude,
            veiculo = veiculoRepository.findByIdentificacao(evento.veiculo),
            tipo = evento.tipo
        )
    }
}