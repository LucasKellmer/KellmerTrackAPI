package kellmertrackbackend.controller

import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.repository.VeiculoRepository
import kellmertrackbackend.service.MotoristaService
import kellmertrackbackend.service.ObraService
import kellmertrackbackend.service.VeiculoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("pesquisa")
class PesquisaController(
    private val motoristaService: MotoristaService,
    private val veiculoService: VeiculoService,
    private val obraService: ObraService
) {

    //motoristas
    @GetMapping("motoristas")
    fun pesquisaMotoristaCombobox() : List<PesquisaDTO>? {
        return motoristaService.pesquisaMotoristaCombobox()
    }

    //veiculos
    @GetMapping("veiculos")
    fun pesquisaVeiculosCombobox(): List<PesquisaDTO> {
        return veiculoService.pesquisaVeiculosCombobox()
    }

    //obras
    @GetMapping("obras")
    fun pesquisaObrasCombobox() : List<PesquisaDTO>?{
        return obraService.pesquisaObraCombobox()
    }

}