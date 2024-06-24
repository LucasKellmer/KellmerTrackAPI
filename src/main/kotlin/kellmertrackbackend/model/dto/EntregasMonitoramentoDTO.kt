package kellmertrackbackend.model.dto

import kellmertrackbackend.model.constants.EntregaStatus

data class EntregasMonitoramentoDTO(
    val id: Int,
    val status: EntregaStatus,
    val latitude: Double,
    val longitude: Double,
    val obraDesc: String,
    val veiculo: String,
    val quantidade: Double,
) {
}