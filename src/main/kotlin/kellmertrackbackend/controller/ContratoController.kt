package kellmertrackbackend.controller

import jakarta.transaction.Transactional
import kellmertrackbackend.model.dto.ContratoFormDTO
import kellmertrackbackend.model.dto.ContratoListDTO
import kellmertrackbackend.model.entities.ContratoEntity
import kellmertrackbackend.service.ContratoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("track")
class ContratoController(
    private val contratoService: ContratoService
) {
    @GetMapping("contratos/pesquisa")
    fun pesquisaContrato(
        @RequestParam(name ="numero") numero: String,
        @RequestParam(name ="empresa") empresa: String,
        @RequestParam(name ="cliente") cliente: String,
    ): ResponseEntity<List<ContratoListDTO>> {
        return ResponseEntity.ok(contratoService.pesquisaContrato(numero, empresa, cliente))
    }

    @PostMapping("contratos")
    fun salvaContrato(@RequestBody contrato : ContratoFormDTO): ResponseEntity<ContratoEntity> {
        return ResponseEntity.ok(contratoService.salvaContrato(contrato))
    }

    @GetMapping("contratos")
    fun buscaContratos() : ResponseEntity<List<ContratoListDTO>> {
        return ResponseEntity.ok(contratoService.buscaContratos())
    }

    @DeleteMapping("contratos")
    @Transactional
    fun deletaContrato(@RequestParam() id: String): ResponseEntity<Void> {
        contratoService.deleteContrato(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("contratos/{numero}")
    fun buscaContratoByCodigo(@PathVariable numero: String): ResponseEntity<ContratoListDTO?> {
        return ResponseEntity.ok(contratoService.buscaContratoById(numero))
    }
}