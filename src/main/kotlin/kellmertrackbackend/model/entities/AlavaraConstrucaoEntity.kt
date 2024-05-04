package kellmertrackbackend.model.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "alvara_construcao")
data class AlvaraConstrucaoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null,
    val indicacaoFiscal: String,
    val inscricaoImobiliaria: String?,
    val dataCriacaoAlvara: Date?,
    val dataInicioObra: Date?,
    val dataConclusaoObra: Date?,
    val logradouro: String,
    val numero: Int,
    val bairro: String,
    val grupoZoneamento: String,
    val abrangencia: String,
    val quantidadePavimentos: Int,
    val quantidadeUnidadesResidenciais: Int?,
    val quantidadeUnidadesNaoResidenciais: Int?,
    val numeroAlvara: String,
    val usoAlvara: String?,
    val sobUsoAlvara: String?,
    val finalidade: String,
    val materiais: String,
    val metragemAreaRemanescente: Double,
    val metragemConstruidaLote: Double,
    val numeroCapacsUtilizadas: Int?,
    val areaAdicionalConstrucao: Double,
    val areaLiberada: Double,
    val metragemAreaReformaAlvara: Double,
    val quantidadeBlocosAlvara: Int,
    val quantidadeSubSoloAlvara: Int,
    val autorProjeto: String?,
    val numeroRegistroCreaCauAu: String?,
    val responsavelTecnico: String?,
    val numeroRegistroCreaCauRt: String?,
    val firmaConstrutora: String?,
    val numeroCvco: String?,
    val tipoVistoria: String?,
    val dataVistoria: String?,
){
}
