package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.VeiculoDTO
import kellmertrackbackend.model.entities.VeiculoEntity
import org.springframework.stereotype.Component

@Component
class VeiculoMapper {

    fun toVeiculoEntity(veiculo : VeiculoDTO) : VeiculoEntity{
        return VeiculoEntity(
            identificacao = veiculo.identificacao,
            descricao = veiculo.descricao
        )
    }

    fun toVeiculoDTO(veiculo : VeiculoEntity?) : VeiculoDTO?{
        return veiculo?.let {
            VeiculoDTO(
                identificacao = it.identificacao,
                descricao = veiculo.descricao
            )
        }
    }
}