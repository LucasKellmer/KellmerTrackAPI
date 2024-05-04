package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.MotoristaDTO
import kellmertrackbackend.model.entities.MotoristaEntity
import org.springframework.stereotype.Component

@Component
class MotoristaMapper {

    fun toMotoristaEntity(motorista : MotoristaDTO) : MotoristaEntity{
        return MotoristaEntity(
            id = motorista.id,
            nome = motorista.nome
        )
    }

    fun toMotoristaDTO(motorista : MotoristaEntity?) : MotoristaDTO?{
        return motorista?.let {
            MotoristaDTO(
                id = it.id,
                nome = motorista.nome
            )
        }
    }
}