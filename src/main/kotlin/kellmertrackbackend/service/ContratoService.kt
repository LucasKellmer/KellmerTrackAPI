package kellmertrackbackend.service

import kellmertrackbackend.model.dto.ContratoFormDTO
import kellmertrackbackend.model.dto.ContratoListDTO
import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.entities.ContratoEntity
import kellmertrackbackend.model.entities.mapper.ContratoMapper
import kellmertrackbackend.repository.ContratoRepository
import org.springframework.stereotype.Service

@Service
class ContratoService(
    private val contratoRepository: ContratoRepository,
    private val contratoMapper: ContratoMapper,
) {
    fun pesquisaContrato(numero : String, empresa: String, cliente : String): List<ContratoListDTO> {
        return contratoRepository.pesquisaContrato(numero, empresa, cliente)
    }

    fun salvaContrato(contrato : ContratoFormDTO) : ContratoEntity {
        val contratoEntity = contratoMapper.toContratoEntity(contrato)
        return contratoRepository.save(contratoEntity)
    }

    fun buscaContratos() : List<ContratoListDTO>{
        val contratosDTO = mutableListOf<ContratoListDTO>()
        contratoRepository.findAll().forEach {
            contratosDTO.add(contratoMapper.toContratoListDTO(it))
        }
        return contratosDTO
    }

    fun deleteContrato(numero : String){
        try {
            return contratoRepository.deleteById(numero)
        }catch (e : Exception){
            throw Exception("Erro ao excluir contrato: ${e.message}")
        }
    }

    fun pesquisaContratosCombobox(): List<PesquisaDTO>? {
        return contratoRepository.pesquisaContratoCombobox()
    }

    fun buscaContratoByNumero(id: String): ContratoFormDTO?{
        val contratoEntity = contratoRepository.findByNumero(id)
        return contratoEntity?.let { contratoMapper.toContratoFormDTO(it) }
    }
}