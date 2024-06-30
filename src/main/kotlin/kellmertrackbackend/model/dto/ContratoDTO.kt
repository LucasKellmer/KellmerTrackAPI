package kellmertrackbackend.model.dto

data class ContratoDTO(
    val numero : String,
    val obra: ObraDTO,
    val empresa : String,
    val cliente : ClienteDTO
) {
}