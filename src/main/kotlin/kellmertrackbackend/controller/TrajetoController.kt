package kellmertrackbackend.controller

import kellmertrackbackend.model.dto.MonitoramentoTrajetoDTO
import kellmertrackbackend.model.dto.TrajetoDiarioDTO
import kellmertrackbackend.model.dto.TrajetoDiarioListDTO
import kellmertrackbackend.service.TrajetoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat

@RequestMapping("track")
@RestController
class TrajetoController(
    private val trajetoService: TrajetoService
) {

    @GetMapping("trajeto/trajeto-diario")
    private fun buscaTrajetos(
        @RequestParam(name = "veiculo") veiculo: String,
        @RequestParam(name = "dataIni") dataIni: String,
        @RequestParam(name = "dataFim") dataFim: String,
    ) : ResponseEntity<List<TrajetoDiarioListDTO>> {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return ResponseEntity.ok(trajetoService.buscaTrajetos(veiculo, formatter.parse(dataIni), formatter.parse(dataFim)))
    }

    @GetMapping("trajeto/{id}")
    private fun buscaTrajetoById(@PathVariable id:Int): ResponseEntity<TrajetoDiarioDTO?> {
        return ResponseEntity.ok(trajetoService.buscaTrajetoDiarioById(id))
    }
}