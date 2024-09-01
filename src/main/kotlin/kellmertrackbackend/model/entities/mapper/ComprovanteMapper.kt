package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.ComprovanteDTO
import kellmertrackbackend.model.entities.ComprovanteEntity
import kellmertrackbackend.repository.EntregaRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ComprovanteMapper (
    private val entregaRepository: EntregaRepository
){

    fun fromDTOToEntity(dto: ComprovanteDTO): ComprovanteEntity {
        val entrega = entregaRepository.findById(dto.entregaId)
        return ComprovanteEntity(
            dto.id ?: UUID.randomUUID().toString(),
            entrega.get(),
            dto.recebedor,
            dto.momento,
            dto.latitude,
            dto.longitude,
            dto.imgComprovante
        )
    }

    fun fromEntityToDTO(entity: ComprovanteEntity): ComprovanteDTO {
        return ComprovanteDTO(
            entity.id,
            entity.entregaId.id,
            entity.recebedor,
            entity.momento,
            entity.latitude,
            entity.longitude,
            null
        )
    }
}