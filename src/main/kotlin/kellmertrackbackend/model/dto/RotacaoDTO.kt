package kellmertrackbackend.model.dto

import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

class RotacaoDTO (
    val id : String?,
    val dispositivo : String?,
    val veiculo : String?,
    val momento : Date?,
    val rpm : Int?,
    val entregaId : Int?,
    val bateria : Int?,
    val temperatura : Int?,
    val direcao : Int?
) {
    override fun toString(): String {
        return try {
            val mapper = ObjectMapper()
            mapper.writeValueAsString(this)
        } catch (e: Exception) {
            ""
        }
    }
}