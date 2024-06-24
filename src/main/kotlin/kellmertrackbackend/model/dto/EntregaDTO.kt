package kellmertrackbackend.model.dto

import java.util.*

data class EntregaDTO(
    val id : Int,
    //@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    val momento: Date,
    val veiculo : String,
    val obra : ObraDTO?,
    val status : Int?,
    val quantidade : Double,
    val dataEntradaUsina : Date?,
    val dataSaidaUsina : Date?,
    val dataEntradaObra : Date?,
    val dataSaidaObra : Date?,
) {
}
