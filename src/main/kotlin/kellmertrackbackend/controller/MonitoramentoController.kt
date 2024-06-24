package kellmertrackbackend.controller

import kellmertrackbackend.model.dto.*
import kellmertrackbackend.service.MonitoramentoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("track")
@RestController
class MonitoramentoController(
    private val monitoramentoService: MonitoramentoService
) {

    @GetMapping("monitoramento/veiculos")
    private fun buscaVeiculos() : ResponseEntity<List<MonitoramentoDTO>> {
        return ResponseEntity.ok(monitoramentoService.buscaVeiculos())
    }

    @GetMapping("monitoramento/usinas")
    private fun buscaEmpresa() : ResponseEntity<List<EmpresaDTO>> {
        return ResponseEntity.ok(monitoramentoService.buscaEmpresas())
    }

    @GetMapping("monitoramento/entregas")
    private fun buscaEntregas() : ResponseEntity<List<EntregasMonitoramentoDTO>> {
        return ResponseEntity.ok(monitoramentoService.buscaEntregas())
    }

    @GetMapping("monitoramento/rotacoes/{entregaId}")
    private fun buscaRotacoesByEntrega(@PathVariable entregaId: Int) : ResponseEntity<List<RotacaoDTO>> {
        return ResponseEntity.ok(monitoramentoService.buscaRotacoesByEntrega(entregaId))
    }
}