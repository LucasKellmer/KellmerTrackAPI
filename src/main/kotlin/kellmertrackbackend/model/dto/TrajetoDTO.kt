package kellmertrackbackend.model.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

class TrajetoDTO @JsonCreator constructor(
    @JsonProperty("id") val id: String?,
    @JsonProperty("dispositivo") val dispositivo: String?,
    @JsonProperty("veiculoId") val veiculoId: String?,
    @JsonProperty("momento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    val momento: Date?,
    @JsonProperty("latitude") val latitude: Double?,
    @JsonProperty("longitude") val longitude: Double?,
    @JsonProperty("velocidade") val velocidade: Int?
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