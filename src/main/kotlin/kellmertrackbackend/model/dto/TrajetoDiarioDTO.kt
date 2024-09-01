package kellmertrackbackend.model.dto

import kellmertrackbackend.model.entities.TrajetoEntity
import java.util.Date

data class TrajetoDiarioDTO(
    val id : Int,
    val data : Date,
    val veiculo : String,
    val trajeto : List<TrajetoEntity>?,
    val rotacoes : List<RotacaoDTO>?
) {
}