package kellmertrackbackend.controller

import jakarta.transaction.Transactional
import kellmertrackbackend.model.dto.EntregaDTO
import kellmertrackbackend.model.dto.EntregaFormDTO
import kellmertrackbackend.model.dto.EntregaListDTO
import kellmertrackbackend.model.entities.EntregaEntity
import kellmertrackbackend.service.EntregaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.*

@RestController
@RequestMapping("track")
class EntregaController(
    private val entregaService: EntregaService
) {
    val formataDatatime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val formatter = SimpleDateFormat("yyyy-MM-dd")

    //entregas
    @GetMapping("entrega/pesquisa")
    fun pesquisaEntregas(
        @RequestParam(name = "descricao") descricao: String,
        @RequestParam(name = "dataIni") dataIni: String,
        @RequestParam(name = "dataFim") dataFim: String,
        @RequestParam(name = "status") status : Int?
    ): ResponseEntity<List<EntregaListDTO>> {
        return ResponseEntity.ok(entregaService.pesquisaEntrega(descricao, formataDatatime.parse(dataIni), formataDatatime.parse(dataFim), status))
    }

    @GetMapping("entrega")
    fun findEntregaById(@RequestParam id : Int) : ResponseEntity<EntregaDTO?> {
        return ResponseEntity.ok(entregaService.findEntregaById(id))
    }

    @GetMapping("entrega-data")
    fun findEntregasByData(@RequestParam data : String) : ResponseEntity<List<EntregaDTO>> {
        val dataIni = formatter.parse(data)
        val calendar = Calendar.getInstance().apply {
            time = dataIni
            add(Calendar.DAY_OF_MONTH, 1)
        }
        val dataFim = calendar.time
        return ResponseEntity.ok(entregaService.findEntregaByData(dataIni, dataFim))
    }

    @PostMapping("entrega")
    fun salvaEntrega(@RequestBody entrega : EntregaFormDTO): ResponseEntity<EntregaEntity> {
        return ResponseEntity.ok(entregaService.salvaEntrega(entrega) )
    }

    @DeleteMapping("entrega")
    @Transactional
    fun deletaEntrega(@RequestParam() id: Int): ResponseEntity<Void> {
        entregaService.deleteEntrega(id)
        return ResponseEntity.ok().build()
    }
}