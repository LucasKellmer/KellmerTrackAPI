package kellmertrackbackend.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import kellmertrackbackend.model.constants.EntregaStatus
import java.util.*

data class EntregaListDTO(
    val id : Int,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    val momento: Date,
    val veiculo : String,
    val obraId : Int,
    val obraDesc : String,
    val status : EntregaStatus?,
    val quantidade : Double,
) {
}