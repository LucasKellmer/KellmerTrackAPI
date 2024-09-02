package kellmertrackbackend.controller

import jakarta.transaction.Transactional
import kellmertrackbackend.model.dto.EmpresaDTO
import kellmertrackbackend.model.dto.ObraDTO
import kellmertrackbackend.model.entities.EmpresaEntity
import kellmertrackbackend.model.entities.ObraEntity
import kellmertrackbackend.model.entities.mapper.ObraMapper
import kellmertrackbackend.service.ObraService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("track")
class ObraController(
    private val obraService: ObraService,
) {

    @GetMapping("obras/pesquisa")
    fun pesquisaDispositivos(
        @RequestParam(name = "descricao") descricao: String,
        @RequestParam(name = "cidade") cidade: String,
    ): ResponseEntity<List<ObraDTO>> {
        return ResponseEntity.ok(obraService.pesquisaObra(descricao, cidade))
    }

    @PostMapping("obras")
    fun salvaObra(@RequestBody obra : ObraDTO): ResponseEntity<ObraEntity> {
        return ResponseEntity.ok(obraService.salvaObra(obra))
    }

    @GetMapping("obras")
    fun buscaObras() : ResponseEntity<List<ObraDTO>>{
        return ResponseEntity.ok(obraService.buscaObras())
    }

    @DeleteMapping("obras")
    @Transactional
    fun deletaObra(@RequestParam() id: Int): ResponseEntity<Void> {
        obraService.deleteObra(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("obras/{id}")
    fun buscaObrById(@PathVariable id: Int?): ResponseEntity<ObraDTO?> {
        return ResponseEntity.ok(obraService.buscaObraById(id))
    }
}