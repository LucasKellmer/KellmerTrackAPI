package kellmertrackbackend.model.dto

import java.util.*

class TrajetoDTO(
    val id : String,
    val dispositivoId : Int,
    val veiculoId : String,
    val momento : Date,
    val latitude : Double,
    val longitude : Double,
    val velocidade : Int
) {
}