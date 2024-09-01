package kellmertrackbackend.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class DispositivoFormDTO (
    val numeroInterno : String,
    val mac : String,
    val modelo : String?,
    val veiculo : String,
    @JsonFormat(pattern = "dd/MM/yyyy")
    val dataVinculo : Date?,
    val motoristaId : Int?,
    val empresa : String?
    //val motoristaNome : String?
    ) {
}