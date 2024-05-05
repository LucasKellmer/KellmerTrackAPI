package kellmertrackbackend.service

import kellmertrackbackend.model.dto.EmpresaDTO
import kellmertrackbackend.model.dto.ObraDTO
import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.entities.EmpresaEntity
import kellmertrackbackend.model.entities.ObraEntity
import kellmertrackbackend.model.entities.mapper.ObraMapper
import kellmertrackbackend.repository.ObraRepository
import org.springframework.stereotype.Service

@Service
class ObraService(
    private val obraRepository: ObraRepository,
    private val obraMapper : ObraMapper
) {

    fun pesquisaObraCombobox(): List<PesquisaDTO>? {
        return obraRepository.pesquisaObraCombobox()
    }

    fun salvaObra(obra : ObraDTO) : ObraEntity {
        val obraEntity = obraMapper.toObraEntity(obra)
        return obraRepository.save(obraEntity)
    }
}