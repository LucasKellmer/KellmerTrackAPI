package kellmertrackbackend.model.dto

data class ContratoFormDTO(
    val numero : String,
    val obra: Int,
    val empresa : String,
    val cliente : Int
) {
}