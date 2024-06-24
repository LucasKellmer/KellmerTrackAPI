package kellmertrackbackend.socket

import com.fasterxml.jackson.databind.ObjectMapper

class SocketMessage(
    val type : String? = null,
    val message : String? = null
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