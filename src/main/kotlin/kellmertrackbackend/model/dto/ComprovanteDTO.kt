package kellmertrackbackend.model.dto

import java.util.*

data class ComprovanteDTO(
    val id : String?,
    val entregaId : Int,
    //val contrato : String,
    val recebedor : String,
    val momento : Date,
    val latitude : Double,
    val longitude : Double,
    var imgComprovante : ByteArray?
) {
}