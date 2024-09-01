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
    fun salvaComprovante(comprovantes : List<ComprovanteDTO>) {
        try {
            comprovantes.forEach {
                val comprovanteEntity = comprovanteMapper.fromDTOToEntity(it)
                comprovanteRepository.save(comprovanteEntity)
            }
        } catch (e : Exception) {
            throw Exception("Não foi possível salvar o comprovante: ${e.stackTrace}")
        }

    }
}