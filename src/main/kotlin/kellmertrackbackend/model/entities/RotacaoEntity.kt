package kellmertrackbackend.model.entities

import kellmertrackbackend.model.dto.RotacaoDTO
import jakarta.persistence.*
import java.util.*

@NamedNativeQuery(
    name = "buscaUltimaRotacao",
    query = "select cast(r.id as text) as id, r.dispositivo_id,\n"+
            " r.veiculo, cast(r.momento as timestamp) as momento, r.rpm \n"+
            " from rotacoes r \n"+
            " where r.veiculo = :identificacao \n"+
            " order by r.momento desc limit 1 \n",
    resultSetMapping = "rotacaoDTO"
)
@SqlResultSetMapping(
    name = "rotacaoDTO",
    classes = [ConstructorResult(
        targetClass = RotacaoDTO::class,
        columns = arrayOf(
            ColumnResult(name = "id"),
            ColumnResult(name = "dispositivo_id"),
            ColumnResult(name = "veiculo"),
            ColumnResult(name = "momento"),
            ColumnResult(name = "rpm"),
        )
    )]
)

@Entity
@Table(name = "rotacoes")
data class RotacaoEntity (
    @Id
    val id : String,
    @OneToOne
    @JoinColumn(name = "dispositivo_id")
    val dispositivo : DispositivoEntity,
    @ManyToOne
    @JoinColumn(name = "veiculo")
    val veiculo : VeiculoEntity?,
    val momento : Date,
    val rpm : Int,
    @ManyToOne
    @JoinColumn(name = "entrega_id")
    val entregaId : EntregaEntity?
){
}