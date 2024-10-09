package kellmertrackbackend.model.dto

data class ContratoFormDTO(
    val numero : String,
    val cliente : Int?,
    val empresa : String?,
    val obra: Int?,
) {
}