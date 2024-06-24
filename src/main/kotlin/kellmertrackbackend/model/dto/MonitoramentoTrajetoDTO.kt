package kellmertrackbackend.model.dto

import java.util.*

data class MonitoramentoTrajetoDTO(
    val id : String?,
    val dispositivoId : Int?,
    val veiculoId : String?,
    val motorista : String?,
    val momento : Date?,
    val latitude : Double?,
    val longitude : Double?,
    val velocidade : Int?,
) {
}