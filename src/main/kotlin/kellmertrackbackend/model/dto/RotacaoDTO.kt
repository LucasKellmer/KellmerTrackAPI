package kellmertrackbackend.model.dto

import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

class RotacaoDTO (
    val id : String?,
    val dispositivoId : Int?,
    val veiculo : String?,
    val momento : Date?,
    val rpm : Int?,
    val entregaId : Int?
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