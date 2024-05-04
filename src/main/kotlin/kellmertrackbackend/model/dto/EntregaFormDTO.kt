package kellmertrackbackend.model.dto

import kellmertrackbackend.model.constants.EntregaStatus
import java.util.*

data class EntregaFormDTO(
    val id : Int,
    val momento: Date,
    val veiculo : String,
    val obra : Int,
    val quantidade : Double
) {

}