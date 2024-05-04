package kellmertrackbackend.model.dto

import java.util.*

data class EventoDTO(
    val id : String,
    val entregaId : Int?,
    val momento : Date,
    val latitude : Double?,
    val longitude : Double?,
    val veiculo : String?,
    val tipo : String
) {
}