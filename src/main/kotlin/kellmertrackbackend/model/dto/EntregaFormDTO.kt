package kellmertrackbackend.model.dto

import java.util.*

data class EntregaFormDTO(
    val id : Int,
    val momento: Date,
    val veiculo : String,
    val obra : Int,
    val quantidade : Double,
    val contrato : String,
) {
}