package kellmertrackbackend.controller

import jakarta.transaction.Transactional
import kellmertrackbackend.model.dto.DispositivoListDTO
import kellmertrackbackend.model.dto.EmpresaDTO
import kellmertrackbackend.model.dto.MotoristaDTO
import kellmertrackbackend.model.entities.EmpresaEntity
import kellmertrackbackend.service.EmpresaService
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("track")
class EmpresaController(
    private val empresaService : EmpresaService
) {

    @GetMapping("empresas/pesquisa")
    fun pesquisaDispositivos(
        @RequestParam(name = "codigo") codigo: String,
        @RequestParam(name = "descricao") descricao: String,
    ): ResponseEntity<List<EmpresaDTO>> {
        return ResponseEntity.ok(empresaService.pesquisaEmpresa(codigo, descricao))
    }

    @PostMapping("empresas")
    fun salvaEmpresa(@RequestBody empresa : EmpresaDTO): ResponseEntity<EmpresaEntity> {
        return ResponseEntity.ok(empresaService.salvaEmpresa(empresa))
    }

    @GetMapping("empresas")
    fun buscaEmpresas() : ResponseEntity<List<EmpresaDTO>>{
        return ResponseEntity.ok(empresaService.buscaEmpresas())
    }

    @DeleteMapping("empresas")
    @Transactional
    fun deletaEmpresa(@RequestParam() id: String): ResponseEntity<Void> {
        empresaService.deleteEmpresa(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("empresas/{codigo}")
    fun buscaEmpresaByCodigo(@PathVariable codigo: String): ResponseEntity<EmpresaDTO?> {
        return ResponseEntity.ok(empresaService.buscaEmpresaByCodigo(codigo))
    }
}