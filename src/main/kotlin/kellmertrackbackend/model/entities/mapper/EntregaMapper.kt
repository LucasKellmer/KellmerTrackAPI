package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.constants.EntregaStatus
import kellmertrackbackend.model.dto.EntregaDTO
import kellmertrackbackend.model.dto.EntregaFirebaseDTO
import kellmertrackbackend.model.dto.EntregaFormDTO
import kellmertrackbackend.model.dto.ObraDTO
import kellmertrackbackend.model.entities.EntregaEntity
import kellmertrackbackend.model.entities.ObraEntity
import kellmertrackbackend.repository.ObraRepository
import kellmertrackbackend.repository.VeiculoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class EntregaMapper(
    private val obraRepository : ObraRepository,
    private val veiculoRepository: VeiculoRepository
) {

    fun toEntregaEntity(entrega : EntregaFormDTO) : EntregaEntity{
        return EntregaEntity(
            id = entrega.id,
            momento = entrega.momento,
            veiculo = veiculoRepository.findByIdentificacao(entrega.veiculo),
            obra = obraRepository.findByIdOrNull(entrega.obra),
            status = EntregaStatus.PENDENTE,
            quantidade = entrega.quantidade,
        )
    }

    fun fromEntregaFirebaseDTOtoEntity(entrega : EntregaFirebaseDTO) : EntregaEntity{
        val status = when (entrega.status) {
            0 -> EntregaStatus.PENDENTE
            1 -> EntregaStatus.TRANSITO
            2 -> EntregaStatus.ENTREGUE
            3 -> EntregaStatus.FINALIZADO
            else -> throw IllegalArgumentException("Unknown status value: ${entrega.status}")
        }
        return EntregaEntity(
            id = entrega.id!!,
            momento = entrega.momento,
            veiculo = veiculoRepository.findByIdentificacao(entrega.veiculo),
            obra = obraRepository.findByIdOrNull(entrega.obraId),
            status = status,
            quantidade = entrega.quantidade.toDouble(),
            dataSaidaUsina = entrega.dataSaidaUsina,
            dataChegadaUsina = entrega.dataEntradaUsina,
            dataSaidaObra = entrega.dataSaidaObra,
            dataChegadaObra = entrega.dataEntradaObra
        )
    }

    fun toEntregaDTO(entrega : EntregaEntity?) : EntregaDTO? {
        return entrega?.let {
            EntregaDTO(
                id = it.id,
                momento = entrega.momento,
                veiculo = entrega.veiculo!!.identificacao,
                obra = criaObraDTO(entrega.obra),
                quantidade = entrega.quantidade,
                status = entrega.status?.ordinal,
                dataEntradaObra = entrega.dataChegadaObra,
                dataEntradaUsina = entrega.dataChegadaUsina,
                dataSaidaObra = entrega.dataSaidaObra,
                dataSaidaUsina = entrega.dataSaidaUsina
            )
        }
    }

    fun criaObraDTO(obra : ObraEntity?): ObraDTO?{
        return obra?.let {
            ObraDTO(
                id = it.id,
                numero = obra.numero,
                complemento = obra.complemento,
                bairro = obra.bairro,
                cidade = obra.cidade,
                descricao = obra.descricao,
                longitude = obra.longitude,
                latitude = obra.latitude,
                raio = obra.raio
            )
        }
    }
}