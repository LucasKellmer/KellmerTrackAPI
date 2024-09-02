package kellmertrackbackend.controller

import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.repository.ContratoRepository
import kellmertrackbackend.repository.VeiculoRepository
import kellmertrackbackend.service.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("pesquisa")
class PesquisaController(
    private val motoristaService: MotoristaService,
    private val veiculoService: VeiculoService,
    private val obraService: ObraService,
    private val empresaService: EmpresaService,
    private val clienteService: ClienteService,
    private val contratoService: ContratoService,
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

    //empresas
    @GetMapping("empresas")
    fun pesquisaEmpresasCombobox() : List<PesquisaDTO>?{
        return empresaService.pesquisaEmpresasCombobox()
    }

    //clientes
    @GetMapping("clientes")
    fun pesquisaClientesCombobox() : List<PesquisaDTO>?{
        return clienteService.pesquisaClientesCombobox()
    }

    //contrato
    @GetMapping("contratos")
    fun pesquisaContratosCombobox() : List<PesquisaDTO>?{
        return contratoService.pesquisaContratosCombobox()
    }
}