package kellmertrackbackend.controller

import jakarta.transaction.Transactional
import kellmertrackbackend.model.dto.MotoristaDTO
import kellmertrackbackend.model.entities.MotoristaEntity
import kellmertrackbackend.service.MotoristaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("track")
class MotoristaController(
    private val motoristaService : MotoristaService
) {

    /*@GetMapping("motoristas")
    fun buscaMotoristas() : ResponseEntity<List<MotoristaEntity>>{
        return ResponseEntity.ok(motoristaService.buscaMotoristas())
    }*/

    //motoristas
    @GetMapping("motorista/pesquisa")
    fun pesquisaMotoristas(
        @RequestParam(name="nome") nome: String,
    ): ResponseEntity<List<MotoristaDTO>> {
        return ResponseEntity.ok(motoristaService.pesquisaMotorista(nome))
    }

    @GetMapping("motorista")
    fun findMotoristaById(@RequestParam id : Int) : ResponseEntity<MotoristaDTO?>{
        return ResponseEntity.ok(motoristaService.findMotoristaById(id))
    }

    @PostMapping("motorista")
    fun salvaMotorista(@RequestBody motorista : MotoristaDTO): ResponseEntity<MotoristaEntity> {
        return ResponseEntity.ok(motoristaService.salvaMotorista(motorista))
    }

    @DeleteMapping("motorista")
    @Transactional
    fun deletaMotorista(@RequestParam() id: Int): ResponseEntity<Void> {
        motoristaService.deleteMotorista(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("motorista-next-id")
    fun nextId() : ResponseEntity<Int>{
        return ResponseEntity.ok(motoristaService.nextId())
    }
}