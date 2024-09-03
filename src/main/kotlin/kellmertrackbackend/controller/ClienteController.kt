package kellmertrackbackend.controller

import jakarta.transaction.Transactional
import kellmertrackbackend.model.dto.ClienteDTO
import kellmertrackbackend.model.entities.ClienteEntity
import kellmertrackbackend.service.ClienteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("track")
class ClienteController(
    private val clienteService: ClienteService
) {

    @GetMapping("clientes/pesquisa")
    fun pesquisaCliente(
        @RequestParam(name = "nome") nome: String,
    ): ResponseEntity<List<ClienteDTO>> {
        return ResponseEntity.ok(clienteService.pesquisaCliente(nome))
    }

    @PostMapping("clientes")
    fun salvaCliente(@RequestBody cliente : ClienteDTO): ResponseEntity<ClienteEntity> {
        return ResponseEntity.ok(clienteService.salvaCliente(cliente))
    }

    @GetMapping("clientes")
    fun buscaClientes() : ResponseEntity<List<ClienteDTO>> {
        return ResponseEntity.ok(clienteService.buscaClientes())
    }

    @DeleteMapping("clientes")
    @Transactional
    fun deletaCliente(@RequestParam() id: String): ResponseEntity<Void> {
        clienteService.deleteCliente(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("clientes/{id}")
    fun buscaClienteByCodigo(@PathVariable id: Int): ResponseEntity<ClienteDTO?> {
        return ResponseEntity.ok(clienteService.buscaClienteById(id))
    }
}