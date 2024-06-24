package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.RotacaoDTO
import kellmertrackbackend.model.entities.RotacaoEntity
import kellmertrackbackend.repository.DispositivoRepository
import kellmertrackbackend.repository.EntregaRepository
import kellmertrackbackend.repository.VeiculoRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class RotacaoMapper(
    private val veiculoRepository: VeiculoRepository,
    private val dispositivoRepository: DispositivoRepository,
    private val entregaRepository: EntregaRepository,
) {
    fun fromDTOtoRotacaoEntity(rotacao: RotacaoDTO): RotacaoEntity {
        return RotacaoEntity(
            id = rotacao.id!!,
            dispositivo = dispositivoRepository.findDispositivoById(rotacao.dispositivoId!!),
            veiculo = veiculoRepository.findByIdentificacao(rotacao.veiculo),
            momento = rotacao.momento!!,
            rpm = rotacao.rpm!!,
            entregaId = entregaRepository.findById(rotacao.entregaId)
        )
    }

    fun fromEntitytoRotacaoDTO(rotacao: List<RotacaoEntity>?): List<RotacaoDTO> {
        val rotacaoDTO = mutableListOf<RotacaoDTO>()
        rotacao?.forEach {
            rotacaoDTO.add(
                RotacaoDTO(
                    id = it.id,
                    dispositivoId = it.dispositivo.id,
                    veiculo = it.veiculo?.identificacao,
                    momento = it.momento,
                    rpm = it.rpm,
                    entregaId = it.entregaId?.id
                )
            )
        }
        return rotacaoDTO
    }
}