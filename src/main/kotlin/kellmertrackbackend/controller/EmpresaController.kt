package kellmertrackbackend.controller

import kellmertrackbackend.model.dto.EmpresaDTO
import kellmertrackbackend.model.entities.EmpresaEntity
import kellmertrackbackend.service.EmpresaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("track")
class EmpresaController(
    private val empresaService : EmpresaService
) {
    @PostMapping("empresa")
    fun salvaDispositivo(@RequestBody empresa : EmpresaDTO): ResponseEntity<EmpresaEntity> {
        return ResponseEntity.ok(empresaService.salvaEmpresa(empresa))
    }
}