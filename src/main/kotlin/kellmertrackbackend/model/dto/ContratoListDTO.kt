package kellmertrackbackend.model.dto

data class ContratoListDTO(
    val numero : String,
    val obra: String?,
    val empresa : String?,
    val cliente : String?
) {
}