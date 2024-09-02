package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.ContratoFormDTO
import kellmertrackbackend.model.dto.ContratoListDTO
import kellmertrackbackend.model.entities.ContratoEntity
import kellmertrackbackend.repository.ClienteRepository
import kellmertrackbackend.repository.EmpresaRepository
import kellmertrackbackend.repository.ObraRepository
import org.springframework.stereotype.Component

@Component
class ContratoMapper(
    private val obraRepository: ObraRepository,
    private val empresaRepository: EmpresaRepository,
    private val clienteRepository: ClienteRepository
) {

    fun toContratoEntity(contrato : ContratoFormDTO) : ContratoEntity {
        return ContratoEntity(
            numero = contrato.numero,
            obraId = obraRepository.findById(contrato.obra).get(),
            empresa = empresaRepository.findByCodigo(contrato.empresa),
            cliente = clienteRepository.findById(contrato.cliente)
        )
    }

    fun toContratoListDTO(contrato : ContratoEntity) : ContratoListDTO {
        return ContratoListDTO(
            numero = contrato.numero,
            obra = contrato.obraId?.descricao,
            empresa = contrato.empresa?.nome,
            cliente = contrato.cliente?.nome
        )
    }
}