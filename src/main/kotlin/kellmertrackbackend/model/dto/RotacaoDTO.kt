package kellmertrackbackend.model.dto

import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

class RotacaoDTO (
    val id : String,
    val dispositivoId : Int,
    val veiculoId : String,
    val momento : Date,
    val rpm : Int,
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