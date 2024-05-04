package kellmertrackbackend.model.dto

import kellmertrackbackend.model.constants.EntregaStatus


data class ObraDTO(
    val id : Int,
    val descricao : String,
    val cidade : String,
    val bairro : String,
    val numero : Int,
    val complemento : String?,
    val latitude : Double,
    val longitude : Double,
    val raio : Double,
){
}