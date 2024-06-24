package kellmertrackbackend.model.dto

data class MonitoramentoDTO(
    val veiculo : String,
    val ultimaLocalizacao : MonitoramentoTrajetoDTO,
    val rotacao : RotacaoDTO
) {
}