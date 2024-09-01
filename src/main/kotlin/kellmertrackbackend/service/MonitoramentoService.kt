package kellmertrackbackend.service

import kellmertrackbackend.model.dto.*
import kellmertrackbackend.model.entities.mapper.RotacaoMapper
import kellmertrackbackend.model.entities.mapper.TrajetoMapper
import kellmertrackbackend.model.entities.mapper.VeiculoMapper
import kellmertrackbackend.repository.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class MonitoramentoService(
    private val rotacaoRepository: RotacaoRepository,
    private val trajetoRepository: TrajetoRepository,
    private val trajetoMapper: TrajetoMapper,
    private val empresaRepository: EmpresaRepository,
    private val entregasRepository: EntregaRepository,
    private val rotacaoMapper: RotacaoMapper,
) {

    fun buscaVeiculos() : List<MonitoramentoDTO>{
        val monitoramento : MutableList<MonitoramentoDTO> = mutableListOf()

        trajetoRepository.buscaVeiculosTrajetos().forEach { veiculo ->

            val ultimaLocalizacao = trajetoMapper.fromEntityToMonitoramentoTrajetoDTO(trajetoRepository.buscaUltimaLocalizacao(veiculo)) ?: criaTrajeto()

            val ultimaRotacao = rotacaoRepository.buscaUltimaRotacao(veiculo) ?: criaRotacao()

            monitoramento.add(MonitoramentoDTO(veiculo, ultimaLocalizacao, ultimaRotacao))
        }
        return monitoramento
    }

    private fun criaTrajeto(): MonitoramentoTrajetoDTO {
        return MonitoramentoTrajetoDTO(
            id = null,
            momento = null,
            latitude = null,
            longitude = null,
            velocidade = null,
            dispositivo = null,
            veiculoId = null,
            motorista = null
        )
    }

    private fun criaRotacao() : RotacaoDTO {
        return RotacaoDTO(
            id = null,
            dispositivo = null,
            veiculo = null,
            momento = null,
            rpm = null,
            entregaId = null,
            bateria = null,
            temperatura = null,
            direcao = null
        )
    }

    fun buscaEmpresas() : List<EmpresaDTO>{
        return empresaRepository.getEmpresas()
    }

    fun buscaEntregas() : List<EntregasMonitoramentoDTO>{
        return entregasRepository.buscaEntregasMonitoramento()
    }

    fun buscaRotacoesByEntrega(entregaId: Int): List<RotacaoDTO>? {
        return rotacaoMapper.fromEntitytoRotacaoDTO(rotacaoRepository.buscaRotacaoByEntrega(entregaId))
    }
}