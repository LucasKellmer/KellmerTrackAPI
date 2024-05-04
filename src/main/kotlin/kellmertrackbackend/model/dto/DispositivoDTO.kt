package kellmertrackbackend.model.dto

import kellmertrackbackend.model.constants.DispositivoModelo
import java.util.*

class DispositivoDTO(
    val id : Int,
    val numeroInterno : String,
    val mac : String,
    val modelo : DispositivoModelo,
    val veiculo : String,
    val dataVinculo : Date?,
    val motoristaId : Int?,
    val motoristaNome : String?,
    val empresa : EmpresaDTO
) {
}