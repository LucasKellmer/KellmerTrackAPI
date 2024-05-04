package kellmertrackbackend.model.mapper

import kellmertrackbackend.model.dto.AlvaraConstrucaoDTO
import kellmertrackbackend.model.entities.AlvaraConstrucaoEntity
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class AlvaraConstrucaoMapper {
    fun toAlvaraConstrucaoEntity(alvara : AlvaraConstrucaoDTO) : AlvaraConstrucaoEntity {
        return AlvaraConstrucaoEntity(
            indicacaoFiscal = alvara.indicacaoFiscal,
            inscricaoImobiliaria = alvara.inscricaoImobiliaria,
            dataCriacaoAlvara =  formataData(alvara.dataCriacaoAlvara),
            dataInicioObra =  formataData(alvara.dataInicioObra),
            dataConclusaoObra = formataData(alvara.dataConclusaoObra),
            logradouro = alvara.logradouro,
            numero = alvara.numero.replace(".", "").toInt(),
            bairro = alvara.bairro,
            grupoZoneamento = alvara.grupoZoneamento,
            abrangencia = alvara.abrangencia,
            quantidadePavimentos = alvara.quantidadePavimentos.toInt(),
            quantidadeUnidadesResidenciais = if(alvara.quantidadeUnidadesResidenciais != "") alvara.quantidadeUnidadesResidenciais?.toInt() else null,
            quantidadeUnidadesNaoResidenciais = if(alvara.quantidadeUnidadesNaoResidenciais != "") alvara.quantidadeUnidadesNaoResidenciais?.toInt() else null,
            numeroAlvara = alvara.numeroAlvara,
            usoAlvara = alvara.usoAlvara,
            sobUsoAlvara = alvara.sobUsoAlvara,
            finalidade = alvara.finalidade,
            materiais = alvara.materiais,
            metragemAreaRemanescente = formataNumero(alvara.metragemAreaRemanescente).toDouble(),
            metragemConstruidaLote = formataNumero(alvara.metragemConstruidaLote).toDouble(),
            numeroCapacsUtilizadas = if(alvara.numeroCapacsUtilizadas != "") alvara.numeroCapacsUtilizadas.toInt() else null,
            areaAdicionalConstrucao = formataNumero(alvara.areaAdicionalConstrucao).toDouble(),
            areaLiberada = formataNumero(alvara.areaLiberada).toDouble(),
            metragemAreaReformaAlvara = formataNumero(alvara.metragemAreaReformaAlvara).toDouble(),
            quantidadeBlocosAlvara = alvara.quantidadeBlocosAlvara.toInt(),
            quantidadeSubSoloAlvara = alvara.quantidadeSubSoloAlvara.toInt(),
            autorProjeto = alvara.autorProjeto,
            numeroRegistroCreaCauAu = alvara.numeroRegistroCreaCauAu,
            responsavelTecnico = alvara.responsavelTecnico,
            numeroRegistroCreaCauRt = alvara.numeroRegistroCreaCauRt,
            firmaConstrutora = alvara.firmaConstrutora,
            numeroCvco = alvara.numeroCvco,
            tipoVistoria = alvara.tipoVistoria,
            dataVistoria = alvara.dataVistoria
        )
    }

    fun formataData(dataAlvara: String?): Date? {
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        var result : Date? = null
        if(dataAlvara != ""){
            val dataFormatada = dataAlvara?.trim()?.replace("/", "-")
            result = sdf.parse(dataFormatada)
        }
        return result
    }

    fun formataNumero(numero : String) : String{
        var numeroFormatado = numero.replace(",",".")
        val tamanhoNumero = numeroFormatado.split(".")
        //se o número tiver dois pontos, tira o primeiro
        if(tamanhoNumero.size >= 3){
            numeroFormatado = tamanhoNumero[0]+tamanhoNumero[1]+"."+tamanhoNumero[2]
        }
        return numeroFormatado
    }
}