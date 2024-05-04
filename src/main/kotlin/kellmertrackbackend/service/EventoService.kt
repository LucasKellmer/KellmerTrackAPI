package kellmertrackbackend.service

import kellmertrackbackend.model.dto.EventoDTO
import kellmertrackbackend.model.entities.mapper.EventoMapper
import kellmertrackbackend.repository.EventoRepository
import org.springframework.stereotype.Service

@Service
class EventoService(
    private val eventoRepository : EventoRepository,
    private val eventoMapper : EventoMapper
) {
    fun salvaEvento(evento: EventoDTO) {
        println("evento recebido para ser salvo: $evento")
        val eventoEntity = eventoMapper.fromEventoDTOtoEventoEntity(evento)
        println("eventoEntity prestes a ser salvo:")
        println(eventoEntity)
        eventoRepository.save(eventoEntity)
    }
}