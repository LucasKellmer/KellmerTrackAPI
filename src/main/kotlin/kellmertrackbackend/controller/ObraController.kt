package kellmertrackbackend.controller

import kellmertrackbackend.model.dto.ObraDTO
import kellmertrackbackend.model.entities.ObraEntity
import kellmertrackbackend.service.ObraService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("track")
class ObraController(
    private val obraService: ObraService
) {
    @PostMapping("obra")
    fun salvaDispositivo(@RequestBody obra : ObraDTO): ResponseEntity<ObraEntity> {
        return ResponseEntity.ok(obraService.salvaObra(obra))
    }
}