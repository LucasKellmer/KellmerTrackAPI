package kellmertrackbackend.service

import kellmertrackbackend.model.dto.RotacaoDTO
import kellmertrackbackend.model.entities.mapper.RotacaoMapper
import kellmertrackbackend.repository.RotacaoRepository
import org.springframework.stereotype.Service

@Service
class SensorRotacaoService(
    private val rotacaoRepository: RotacaoRepository,
    private val rotacaoMapper: RotacaoMapper
) {
    fun salvaRotacao(rotacao: RotacaoDTO) {
        val rotacaoEntity = rotacaoMapper.fromDTOtoRotacaoEntity(rotacao)
        rotacaoRepository.save(rotacaoEntity)
    }
}
