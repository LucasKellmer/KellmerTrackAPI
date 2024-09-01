package kellmertrackbackend.model.dto

import java.util.*

data class DispositivoListDTO(
    val numeroInterno : String,
    val mac : String,
    val modelo : String,
    val veiculo : String,
    val dataVinculo : Date?,
    val motoristaId : Int?,
    val motoristaNome : String?,
    val empresa : String?
) {
}