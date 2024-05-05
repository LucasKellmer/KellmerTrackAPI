package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.ObraDTO
import kellmertrackbackend.model.entities.ObraEntity
import org.springframework.stereotype.Component

@Component
class ObraMapper {

    fun toObraEntity(obra : ObraDTO) : ObraEntity{
        return ObraEntity(
            id = obra.id,
            descricao = obra.descricao,
            numero = obra.numero,
            cidade = obra.cidade,
            bairro = obra.bairro,
            complemento = obra.complemento,
            latitude = obra.latitude,
            longitude = obra.longitude,
            raio = obra.raio
        )
    }
}