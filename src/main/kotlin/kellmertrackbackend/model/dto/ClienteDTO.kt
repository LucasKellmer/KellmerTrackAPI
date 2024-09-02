package kellmertrackbackend.model.dto

data class ClienteDTO(
    val id: Int?,
    val nome: String,
    val cpf: String,
    val cnpj: String?,
    val email: String?
) {
}