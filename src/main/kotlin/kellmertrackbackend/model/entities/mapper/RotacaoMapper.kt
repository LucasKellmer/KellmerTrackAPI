package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.RotacaoDTO
import kellmertrackbackend.model.entities.RotacaoEntity
import kellmertrackbackend.repository.DispositivoRepository
import kellmertrackbackend.repository.VeiculoRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class RotacaoMapper(
    private val veiculoRepository: VeiculoRepository,
    private val dispositivoRepository: DispositivoRepository,
) {
    fun fromDTOtoRotacaoEntity(rotacao: RotacaoDTO): RotacaoEntity {
        return RotacaoEntity(
            id = UUID.fromString(rotacao.id),
            dispositivo = dispositivoRepository.findDispositivoById(rotacao.dispositivoId),
            veiculo = veiculoRepository.findByIdentificacao(rotacao.veiculoId),
            momento = rotacao.momento,
            rpm = rotacao.rpm
        )
    }
}