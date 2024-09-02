package kellmertrackbackend.model.dto

data class EmpresaDTO (
    val codigo : String,
    val descricao : String,
    val latitude : Double,
    val longitude : Double,
    val raio : Double
){
}