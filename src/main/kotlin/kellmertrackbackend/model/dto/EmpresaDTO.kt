package kellmertrackbackend.model.dto

data class EmpresaDTO (
    val codigo : String,
    val nome : String,
    val latitude : Double,
    val longitude : Double,
    val raio : Double
){
}