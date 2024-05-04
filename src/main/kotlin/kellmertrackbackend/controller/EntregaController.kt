package kellmertrackbackend.controller

import jakarta.transaction.Transactional
import kellmertrackbackend.model.constants.EntregaStatus
import kellmertrackbackend.model.dto.EntregaDTO
import kellmertrackbackend.model.dto.EntregaFormDTO
import kellmertrackbackend.model.dto.EntregaListDTO
import kellmertrackbackend.model.entities.EntregaEntity
import kellmertrackbackend.service.EntregaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat

@RestController
@RequestMapping("track")
class EntregaController(
    private val entregaService: EntregaService
) {
    //entregas
    @GetMapping("entrega/pesquisa")
    fun pesquisaEntregas(
        @RequestParam(name = "descricao") descricao: String,
        @RequestParam(name = "dataIni") dataIni: String,
        @RequestParam(name = "dataFim") dataFim: String,
        @RequestParam(name = "status") status : Int?
    ): ResponseEntity<List<EntregaListDTO>> {
        val formataData = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return ResponseEntity.ok(entregaService.pesquisaEntrega(descricao, formataData.parse(dataIni), formataData.parse(dataFim), status))
    }

    @GetMapping("entrega")
    fun findEntregaById(@RequestParam id : Int) : ResponseEntity<EntregaDTO?> {
        return ResponseEntity.ok(entregaService.findEntregaById(id))
    }

    @PostMapping("entrega")
    fun salvaDevice(@RequestBody entrega : EntregaFormDTO): ResponseEntity<EntregaEntity> {
        println(entrega)
        return ResponseEntity.ok(entregaService.salvaEntrega(entrega) )
    }

    @DeleteMapping("entrega")
    @Transactional
    fun deletaEntrega(@RequestParam() id: Int): ResponseEntity<Void> {
        entregaService.deleteEntrega(id)
        return ResponseEntity.ok().build()
    }
}