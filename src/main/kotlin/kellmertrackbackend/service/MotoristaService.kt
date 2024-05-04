package kellmertrackbackend.service

import kellmertrackbackend.model.dto.MotoristaDTO
import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.entities.MotoristaEntity
import kellmertrackbackend.model.entities.mapper.MotoristaMapper
import kellmertrackbackend.repository.MotoristaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MotoristaService(
    private val motoristaRepository: MotoristaRepository,
    private val motoristaMapper: MotoristaMapper
) {

    fun pesquisaMotoristaCombobox(): List<PesquisaDTO>? {
        return motoristaRepository.pesquisaMotoristaCombobox()
    }

    fun pesquisaMotorista(nome : String): List<MotoristaDTO> {
        return motoristaRepository.pesquisaMotoristas(nome)
    }

    fun findMotoristaById(id: Int): MotoristaDTO? {
        return motoristaMapper.toMotoristaDTO(motoristaRepository.findByIdOrNull(id))
    }

    fun salvaMotorista(motorista: MotoristaDTO): MotoristaEntity {
        val motoristaEntity = motoristaMapper.toMotoristaEntity(motorista)
        try {
            return motoristaRepository.save(motoristaEntity)
        } catch (e: Exception) {
            throw Exception("Ocorreu um erro ao salvar motorista! ${e.message}")
        }

    }

    fun deleteMotorista(id : Int){
        try {
            return motoristaRepository.deleteById(id)
        }catch (e : Exception){
            throw Exception("Erro ao excluir motorista: ${e.message}")
        }
    }

    fun nextId(): Int{
        return motoristaRepository.nextId()
    }
}