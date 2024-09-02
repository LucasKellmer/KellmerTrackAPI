package kellmertrackbackend.service

import kellmertrackbackend.model.dto.EmpresaDTO
import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.entities.EmpresaEntity
import kellmertrackbackend.model.entities.mapper.EmpresaMapper
import kellmertrackbackend.repository.EmpresaRepository
import org.springframework.stereotype.Service

@Service
class EmpresaService(
    private val empresaRepository : EmpresaRepository,
    private val empresaMapper : EmpresaMapper
) {

    fun pesquisaEmpresa(codigo: String, descricao : String): List<EmpresaDTO> {
        return empresaRepository.pesquisaEmpresa(codigo, descricao)
    }

    fun salvaEmpresa(empresa : EmpresaDTO) : EmpresaEntity{
        val empresaEntity = empresaMapper.toEmpresaEntity(empresa)
        return empresaRepository.save(empresaEntity)
    }

    fun buscaEmpresas() : List<EmpresaDTO>{
        val empresasDTO = mutableListOf<EmpresaDTO>()
        empresaRepository.findAll().forEach {
            empresasDTO.add(empresaMapper.toEmpresaDTO(it))
        }
        return empresasDTO
    }

    fun deleteEmpresa(id : String){
        try {
            return empresaRepository.deleteById(id)
        }catch (e : Exception){
            throw Exception("Erro ao excluir empresa: ${e.message}")
        }
    }

    fun pesquisaEmpresasCombobox(): List<PesquisaDTO>? {
        return empresaRepository.pesquisaEmpresaCombobox()
    }

    fun buscaEmpresaByCodigo(codigo: String): EmpresaDTO?{
        val empresaEntity = empresaRepository.findByCodigo(codigo)
        return empresaEntity?.let { empresaMapper.toEmpresaDTO(it) }
    }
}