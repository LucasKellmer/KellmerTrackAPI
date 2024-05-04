package kellmertrackbackend.controller

import jakarta.transaction.Transactional
import kellmertrackbackend.model.dto.VeiculoDTO
import kellmertrackbackend.model.entities.DispositivoEntity
import kellmertrackbackend.model.entities.VeiculoEntity
import kellmertrackbackend.service.VeiculoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("track")
class VeiculosController(
    private val veiculoService: VeiculoService
) {

    @GetMapping("veiculo/pesquisa")
    fun pesquisaVeiculos(
        @RequestParam(name="identificacao") identificacao: String,
        @RequestParam(name = "descricao") descricao : String
    ): ResponseEntity<List<VeiculoDTO>> {
        return ResponseEntity.ok(veiculoService.pesquisaVeiculos(identificacao, descricao))
    }

    @GetMapping("veiculo")
    fun findVeiculoByIdentificacao(@RequestParam identificacao : String) : ResponseEntity<VeiculoDTO?> {
        return ResponseEntity.ok(veiculoService.findVeiculoByIdentificacao(identificacao))
    }

    @PostMapping("veiculo")
    fun salvaVeiculo(@RequestBody veiculo : VeiculoDTO): ResponseEntity<VeiculoEntity> {
        return ResponseEntity.ok(veiculoService.salvaVeiculo(veiculo))
    }

    @DeleteMapping("veiculo")
    @Transactional
    fun deletaVeiculo(@RequestParam() identificacao: String): ResponseEntity<Void> {
        veiculoService.deleteVeiculo(identificacao)
        return ResponseEntity.ok().build()
    }
}