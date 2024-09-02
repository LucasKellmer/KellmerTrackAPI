package kellmertrackbackend.service

import kellmertrackbackend.model.dto.ObraDTO
import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.entities.ObraEntity
import kellmertrackbackend.model.entities.mapper.ObraMapper
import kellmertrackbackend.repository.ObraRepository
import org.springframework.stereotype.Service

@Service
class ObraService(
    private val obraRepository: ObraRepository,
    private val obraMapper : ObraMapper
) {

    fun salvaObra(obra : ObraDTO) : ObraEntity {
        val obraEntity = obraMapper.toObraEntity(obra)
        return obraRepository.save(obraEntity)
    }

    fun pesquisaObra(descricao : String, cidade: String, ): List<ObraDTO> {
        return obraRepository.pesquisaObra(descricao, cidade)
    }


    fun buscaObras() : List<ObraDTO>{
        val empresasDTO = mutableListOf<ObraDTO>()
        obraRepository.findAll().forEach {
            empresasDTO.add(obraMapper.toObraDTO(it))
        }
        return empresasDTO
    }

    fun deleteObra(id : Int){
        try {
            return obraRepository.deleteById(id)
        }catch (e : Exception){
            throw Exception("Erro ao excluir empresa: ${e.message}")
        }
    }

    fun pesquisaObraCombobox(): List<PesquisaDTO>? {
        return obraRepository.pesquisaObraCombobox()
    }

    fun buscaObraById(id: Int?): ObraDTO?{
        val empresaEntity = obraRepository.findById(id)
        return empresaEntity?.let { obraMapper.toObraDTO(it) }
    }
}