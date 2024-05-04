package kellmertrackbackend.service

import kellmertrackbackend.exception.InvalidRegistryException
import kellmertrackbackend.model.dto.VeiculoDTO
import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.entities.VeiculoEntity
import kellmertrackbackend.model.entities.mapper.VeiculoMapper
import kellmertrackbackend.repository.VeiculoRepository
import org.springframework.stereotype.Service

@Service
class VeiculoService(
    private val veiculoRepository: VeiculoRepository,
    private val veiculoMapper: VeiculoMapper
) {

    fun pesquisaVeiculosCombobox(): List<PesquisaDTO> {
        return veiculoRepository.pesquisaVeiculosCombobox()
    }

    fun pesquisaVeiculos(identificacao: String, descricao : String): List<VeiculoDTO> {
        return veiculoRepository.pesquisaVeiculos(identificacao, descricao)
    }

    fun findVeiculoByIdentificacao(identificacao: String): VeiculoDTO? {
        return veiculoMapper.toVeiculoDTO(veiculoRepository.findByIdentificacao(identificacao))
    }

    fun salvaVeiculo(veiculo: VeiculoDTO): VeiculoEntity {
        val veiculoEntity = veiculoMapper.toVeiculoEntity(veiculo)
        try {
            if (veiculoRepository.findByIdentificacao(veiculo.identificacao) != null && veiculo.identificacao.isNotEmpty())
                throw InvalidRegistryException("Já existe um veículo cadastrado com essa identificação! Verifique!")
            return veiculoRepository.save(veiculoEntity)
        } catch (e: Exception) {
            throw Exception("Ocorreu um erro ao salvar veiculo! ${e.message}")
        }
    }

    fun deleteVeiculo(identificacao : String){
        try {
            return veiculoRepository.deleteById(identificacao)
        }catch (e : Exception){
            throw Exception("Erro ao excluir veiculo: ${e.message}")
        }
    }
}