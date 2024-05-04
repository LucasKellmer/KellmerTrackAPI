package kellmertrackbackend.service

import kellmertrackbackend.model.dto.TrajetoDTO
import kellmertrackbackend.model.entities.TrajetoEntity
import kellmertrackbackend.model.entities.mapper.TrajetoMapper
import kellmertrackbackend.repository.TrajetoRepository
import org.springframework.stereotype.Service

@Service
class TrajetoService(
    private val trajetoRepository: TrajetoRepository,
    private val trajetoMapper: TrajetoMapper,
) {
    fun salvaTrajeto(trajeto: TrajetoDTO) {
        val trajetoEntity = trajetoMapper.fromDTOtoTrajetoEntity(trajeto)
        trajetoRepository.save(trajetoEntity)
    }

    fun buscaTodosTrajetos() : MutableList<TrajetoEntity> {
        return trajetoRepository.findAll()
    }
}