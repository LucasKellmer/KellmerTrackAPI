package kellmertrackbackend.service

import kellmertrackbackend.model.dto.EmpresaDTO
import kellmertrackbackend.model.dto.EntregaFirebaseDTO
import kellmertrackbackend.model.entities.EmpresaEntity
import kellmertrackbackend.model.entities.mapper.EmpresaMapper
import kellmertrackbackend.repository.EmpresaRepository
import org.springframework.stereotype.Service

@Service
class EmpresaService(
    private val empresaRepository : EmpresaRepository,
    private val empresaMapper : EmpresaMapper
) {

    fun salvaEmpresa(empresa : EmpresaDTO) : EmpresaEntity{
        val empresaEntity = empresaMapper.toEmpresaEntity(empresa)
        return empresaRepository.save(empresaEntity)
    }
}