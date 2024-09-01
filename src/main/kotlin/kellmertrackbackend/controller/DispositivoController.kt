package kellmertrackbackend.controller

import jakarta.transaction.Transactional
import kellmertrackbackend.model.dto.DispositivoDTO
import kellmertrackbackend.model.dto.DispositivoFormDTO
import kellmertrackbackend.model.dto.DispositivoListDTO
import kellmertrackbackend.model.entities.DispositivoEntity
import kellmertrackbackend.service.DispositivoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("track")
class DispositivoController(
    private val dispositivoService: DispositivoService
) {
    //dispositivos
    @GetMapping("dispositivos/pesquisa")
    fun pesquisaDispositivos(
        @RequestParam(name = "numeroInterno") numeroInterno: String,
        @RequestParam(name = "motoristaId") motoristaId: Int,
        @RequestParam(name = "veiculoId") veiculoId: String,
        @RequestParam(name = "mac") mac: String,
    ): ResponseEntity<List<DispositivoListDTO>> {
        return ResponseEntity.ok(dispositivoService.pesquisaDispositivo(numeroInterno, motoristaId, veiculoId, mac))
    }

    @GetMapping("dispositivo")
    fun findDispositivoByNumeroInterno(@RequestParam numeroInterno: String) : ResponseEntity<DispositivoFormDTO?>{
        return ResponseEntity.ok(dispositivoService.findByNumeroInternoFormDTO(numeroInterno))
    }

    @PostMapping("dispositivo")
    fun salvaDispositivo(@RequestBody dispositivo : DispositivoFormDTO): ResponseEntity<DispositivoEntity> {
        return ResponseEntity.ok(dispositivoService.salvaDispositivo(dispositivo) )
    }

    @DeleteMapping("dispositivo")
    @Transactional
    fun deletaDispositivo(@RequestParam() id: Int): ResponseEntity<Void> {
        dispositivoService.deleteDispositivo(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("dispositivo-numero")
    fun validaNumeroInterno(@RequestParam numeroInterno: String?) : DispositivoEntity?{
        println("numeroInterno parametro: $numeroInterno")
        val result = dispositivoService.findByNumeroInterno(numeroInterno)
        println("result: $result")
        return dispositivoService.findByNumeroInterno(numeroInterno)
    }
    @GetMapping("dispositivo-veiculo")
    fun validaVeiculo(@RequestParam veiculo: String?) : DispositivoEntity?{
        return dispositivoService.findByVeiculo(veiculo)
    }
    @GetMapping("dispositivo-mac")
    fun validaMac(@RequestParam mac: String?) : DispositivoEntity?{
        return dispositivoService.findByMac(mac)
    }

    @GetMapping("dispositivo-motorista")
    fun validaMotorista(@RequestParam motorista : Int) : DispositivoEntity?{
        return dispositivoService.findByMotorista(motorista)
    }

    @PutMapping("dispositivo-vinculo/{numeroInterno}")
    @Transactional
    fun limpaDataVinculo(@PathVariable numeroInterno : String) : ResponseEntity<Void>{
        dispositivoService.limpaDataVinculo(numeroInterno)
        return ResponseEntity.ok().build()
    }
}