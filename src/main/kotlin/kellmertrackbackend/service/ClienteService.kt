package kellmertrackbackend.service

import kellmertrackbackend.model.dto.ClienteDTO
import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.entities.ClienteEntity
import kellmertrackbackend.model.entities.mapper.ClienteMapper
import kellmertrackbackend.repository.ClienteRepository
import org.springframework.stereotype.Service

@Service
class ClienteService(
    private val clienteRepository: ClienteRepository,
    private val clienteMapper : ClienteMapper,
) {

    fun pesquisaCliente(nome : String): List<ClienteDTO> {
        return clienteRepository.pesquisaCliente(nome)
    }

    fun salvaCliente(cliente : ClienteDTO) : ClienteEntity {
        val clienteEntity = clienteMapper.toClienteEntity(cliente)
        return clienteRepository.save(clienteEntity)
    }

    fun buscaClientes() : List<ClienteDTO>{
        val empresasDTO = mutableListOf<ClienteDTO>()
        clienteRepository.findAll().forEach {
            empresasDTO.add(clienteMapper.toClienteDTO(it))
        }
        return empresasDTO
    }

    fun deleteCliente(id : String){
        try {
            return clienteRepository.deleteById(id)
        }catch (e : Exception){
            throw Exception("Erro ao excluir cliente: ${e.message}")
        }
    }

    fun pesquisaClientesCombobox(): List<PesquisaDTO>? {
        return clienteRepository.pesquisaClienteCombobox()
    }

    fun buscaClienteById(id: Int): ClienteDTO?{
        val clienteEntity = clienteRepository.findById(id)
        return clienteEntity?.let { clienteMapper.toClienteDTO(it) }
    }
}