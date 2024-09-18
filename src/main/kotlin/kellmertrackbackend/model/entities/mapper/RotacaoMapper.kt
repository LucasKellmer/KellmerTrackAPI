package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.RotacaoDTO
import kellmertrackbackend.model.entities.RotacaoEntity
import kellmertrackbackend.repository.DispositivoRepository
import kellmertrackbackend.repository.EntregaRepository
import kellmertrackbackend.repository.VeiculoRepository
import org.springframework.stereotype.Component

@Component
class RotacaoMapper(
    private val veiculoRepository: VeiculoRepository,
    private val dispositivoRepository: DispositivoRepository,
    private val entregaRepository: EntregaRepository,
) {
    fun fromDTOtoRotacaoEntity(rotacao: RotacaoDTO): RotacaoEntity {
        return RotacaoEntity(
            id = rotacao.id!!,
            dispositivo = dispositivoRepository.findByNumeroInterno(rotacao.dispositivo)!!,
            veiculo = veiculoRepository.findByIdentificacao(rotacao.veiculo),
            momento = rotacao.momento!!,
            rpm = rotacao.rpm!!,
            entregaId = if(rotacao.entregaId != null) entregaRepository.findById(rotacao.entregaId).get() else null,
            bateria = rotacao.bateria,
            temperatura = rotacao.temperatura,
            direcao = rotacao.direcao,
        )
    }

    fun fromEntitytoRotacaoDTO(rotacao: List<RotacaoEntity>?): List<RotacaoDTO> {
        val rotacaoDTO = mutableListOf<RotacaoDTO>()
        rotacao?.forEach {
            rotacaoDTO.add(
                RotacaoDTO(
                    id = it.id,
                    dispositivo = it.dispositivo.numeroInterno,
                    veiculo = it.veiculo?.identificacao,
                    momento = it.momento,
                    rpm = it.rpm,
                    entregaId = it.entregaId?.id,
                    bateria = it.bateria,
                    temperatura = it.temperatura,
                    direcao = it.direcao,
                )
            )
        }
        return rotacaoDTO
    }
}