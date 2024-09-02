package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.ClienteDTO
import kellmertrackbackend.model.entities.ClienteEntity
import org.springframework.stereotype.Component

@Component
class ClienteMapper {

    fun toClienteEntity(cliente : ClienteDTO): ClienteEntity {
        return ClienteEntity(
            id = cliente.id,
            nome = cliente.nome,
            cpf = cliente.cpf,
            cnpj = cliente.cnpj,
            email = cliente.email,
        )
    }

    fun toClienteDTO(cliente : ClienteEntity): ClienteDTO {
        return ClienteDTO(
            id = cliente.id,
            nome = cliente.nome,
            cpf = cliente.cpf,
            cnpj = cliente.cnpj,
            email = cliente.email,
        )
    }
}