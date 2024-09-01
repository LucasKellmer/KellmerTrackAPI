package kellmertrackbackend.model.dto

import kellmertrackbackend.model.constants.DispositivoModelo
import java.util.*

class DispositivoDTO(
    val numeroInterno : String,
    val mac : String,
    val modelo : String?,
    val veiculo : String,
    val dataVinculo : Date?,
    val motoristaId : Int?,
    val motoristaNome : String?,
    val empresa : EmpresaDTO
) {
}