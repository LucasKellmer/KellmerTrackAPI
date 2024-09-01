package kellmertrackbackend.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class TrajetoDiarioListDTO(
    val id : Int,
    @JsonFormat(pattern = "dd/MM/yyyy")
    val data : Date,
    val veiculo : String,
) {
}