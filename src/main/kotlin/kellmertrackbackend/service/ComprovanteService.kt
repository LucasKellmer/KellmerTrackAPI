package kellmertrackbackend.service

import kellmertrackbackend.model.dto.ComprovanteDTO
import kellmertrackbackend.model.entities.mapper.ComprovanteMapper
import kellmertrackbackend.repository.ComprovanteRepository
import org.springframework.stereotype.Service

@Service
class ComprovanteService (
    private val comprovanteRepository: ComprovanteRepository,
    private val comprovanteMapper: ComprovanteMapper
){
    fun salvaComprovante(comprovante : ComprovanteDTO){
        comprovanteRepository.save(comprovanteMapper.fromDTOToEntity(comprovante))
    }
}