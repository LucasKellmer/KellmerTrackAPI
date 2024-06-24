package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.ComprovanteDTO
import kellmertrackbackend.model.entities.ComprovanteEntity
import kellmertrackbackend.repository.EntregaRepository
import org.springframework.stereotype.Component

@Component
class ComprovanteMapper (
    private val entregaRepository: EntregaRepository
){

    fun fromDTOToEntity(dto: ComprovanteDTO): ComprovanteEntity {
        val entrega = entregaRepository.findById(dto.entregaId)
        return ComprovanteEntity(
            dto.id,
            entrega.get(),
            dto.recebedor,
            dto.momento,
            dto.latitude,
            dto.longitude,
            dto.imgComprovante
        )
    }
}