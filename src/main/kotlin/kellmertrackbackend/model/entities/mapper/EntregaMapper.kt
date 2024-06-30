package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.constants.EntregaStatus
import kellmertrackbackend.model.dto.*
import kellmertrackbackend.model.entities.ClienteEntity
import kellmertrackbackend.model.entities.ContratoEntity
import kellmertrackbackend.model.entities.EntregaEntity
import kellmertrackbackend.model.entities.ObraEntity
import kellmertrackbackend.repository.ContratoRepository
import kellmertrackbackend.repository.ObraRepository
import kellmertrackbackend.repository.VeiculoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class EntregaMapper(
    private val veiculoRepository: VeiculoRepository,
    private val contratoRepository: ContratoRepository,
) {

    fun toEntregaEntity(entrega : EntregaFormDTO) : EntregaEntity{
        return EntregaEntity(
            id = entrega.id,
            momento = entrega.momento,
            veiculo = veiculoRepository.findByIdentificacao(entrega.veiculo),
            status = EntregaStatus.PENDENTE,
            quantidade = entrega.quantidade,
            contrato = contratoRepository.findById(entrega.contrato).get()
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
            status = status,
            contrato = contratoRepository.findById(entrega.contrato).get(),
            quantidade = entrega.quantidade.toDouble(),
            quantidadeEntregue = entrega.quantidadeEntregue.toDouble(),
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
                quantidade = entrega.quantidade,
                contrato = criaContratoDTO(entrega.contrato),
                status = entrega.status?.ordinal,
                dataEntradaObra = entrega.dataChegadaObra,
                dataEntradaUsina = entrega.dataChegadaUsina,
                dataSaidaObra = entrega.dataSaidaObra,
                dataSaidaUsina = entrega.dataSaidaUsina
            )
        }
    }

    fun criaContratoDTO(contrato : ContratoEntity) : ContratoDTO{
        return ContratoDTO(
            numero = contrato.numero,
            obra = criaObraDTO(contrato.obraId),
            cliente = criaClienteDTO(contrato.cliente),
            empresa = contrato.empresa.codigo
        )
    }

    fun criaClienteDTO(cliente : ClienteEntity) : ClienteDTO{
        return ClienteDTO(
            id = cliente.id!!,
            nome = cliente.nome,
            cpf = cliente.cpf,
            cnpj = cliente.cnpj,
            email = cliente.email,
        )
    }

    fun criaObraDTO(obra : ObraEntity): ObraDTO{
        return ObraDTO(
                id = obra.id,
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