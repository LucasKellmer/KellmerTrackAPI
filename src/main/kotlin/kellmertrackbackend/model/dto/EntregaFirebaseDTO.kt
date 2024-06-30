package kellmertrackbackend.model.dto

import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

data class EntregaFirebaseDTO(
    val id : Int? = null,
    val momento: Date,
    val veiculo : String,
    val status : Int?,
    val contrato : String,
    val quantidade : Int,
    val quantidadeEntregue : Int,
    val dataSaidaObra : Date?,
    val dataEntradaObra : Date?,
    val dataSaidaUsina : Date?,
    val dataEntradaUsina : Date?,
    val sincronizado : Boolean?
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