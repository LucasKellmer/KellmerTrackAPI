package kellmertrackbackend.model.dto

import java.util.*

data class EntregaFirebaseDTO(
    val id : Int? = null,
    val momento: Date,
    val veiculo : String,
    val obraId : Int,
    val status : Int?,
    val quantidade : Int,
    val dataSaidaObra : Date?,
    val dataEntradaObra : Date?,
    val dataSaidaUsina : Date?,
    val dataEntradaUsina : Date?,
    val sincronizado : Boolean?
) {
}