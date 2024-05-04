package kellmertrackbackend.controller

import kellmertrackbackend.model.dto.DispositivoDTO
import kellmertrackbackend.model.dto.EntregaDTO
import kellmertrackbackend.model.dto.RotacaoDTO
import kellmertrackbackend.model.dto.TrajetoDTO
import kellmertrackbackend.service.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("track")
class KellmerTrackController(
    private val dispositivoService : DispositivoService,
    private val sensorRotacaoService : SensorRotacaoService,
    private val trajetoService : TrajetoService,
    private val entregaService: EntregaService,
    private val entregasConsumoService: EntregasConsumoService
) {

    @GetMapping("setup/{numeroInterno}")
    fun buscaDisipositivo(@PathVariable numeroInterno : String) : ResponseEntity<DispositivoDTO> {
        println("numero interno recebido como parametro: $numeroInterno")
        return try{
            val dispositivos = dispositivoService.findDispositivoByNumeroInterno(numeroInterno)
            ResponseEntity.ok(dispositivos)
        }catch (e: IndexOutOfBoundsException){
            val dispositivos : DispositivoDTO? = null
            println("Erro: ${e.message}")
            ResponseEntity.ofNullable(dispositivos)
        }
    }

    @PutMapping("setup/{numeroInterno}")
    fun atualizaDataVinculo(@PathVariable numeroInterno : String){
        try {
            dispositivoService.atualizaDataVinculo(numeroInterno)
        }catch (e: Exception){
            println("Erro : ${e.message}")
        }
    }

    //rotacao
    @PostMapping("rotacao")
    @ResponseStatus(HttpStatus.CREATED)
    fun salvaRotacao(@RequestBody rotacao : RotacaoDTO) {
        return sensorRotacaoService.salvaRotacao(rotacao)
    }

    //trajeto
    @PostMapping("trajeto")
    @ResponseStatus(HttpStatus.CREATED)
    fun salvaTrajeto(@RequestBody trajeto : TrajetoDTO) {
        println("=========== salvando trajeto")
        return trajetoService.salvaTrajeto(trajeto)
    }

    //entrega
    @GetMapping("entrega/{entregaId}")
    fun buscaEntrega(@PathVariable entregaId : String) : ResponseEntity<EntregaDTO?> {
        println("BuscaEntrega chamado")
        val entregas = this.entregaService.findEntregaById(entregaId.toInt())
        println(entregas)
        return ResponseEntity.ok(entregas)
    }

    @PostMapping("/entrega/{id}")
    fun confirmaEntregaRecebida(@PathVariable id: Int) : ResponseEntity<Void> {
        entregasConsumoService.atualizaMomentoConsumo(id)
        return ResponseEntity.ok().build()
    }

    @PostMapping("entrega/{id}/{status}")
    fun atualizaEntregaStatus(@PathVariable id : Int, @PathVariable status : Int) : ResponseEntity<Void> {
        entregaService.atualizaEntregaStatus(id, status)
        return ResponseEntity.ok().build()
    }

    @PostMapping("descarregamento/{entregaId}")
    fun descarregamento(@PathVariable entregaId : Int) : ResponseEntity<Void> {
        //entregaService.descarregamento(entregaId)
        println("========================== descarregamento chamado")
        return ResponseEntity.ok().build()
    }


}