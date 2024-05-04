package kellmertrackbackend.model.entities

import kellmertrackbackend.model.dto.RotacaoDTO
import jakarta.persistence.*
import java.util.*

@NamedNativeQuery(
    name = "buscaUltimaRotacao",
    query = "select cast(r.id as text) as id, r.sernsortrack_dispositivo_rotacao_id,\n"+
            " r.veiculo_id, cast(r.momento as timestamp) as momento, r.rpm \n"+
            " from grupo.hobitrack_rotacoes r \n"+
            " where r.veiculos_id = :identificacao \n"+
            " order by r.momento desc limit 1 \n",
    resultSetMapping = "rotacaoDTO"
)
@SqlResultSetMapping(
    name = "rotacaoDTO",
    classes = [ConstructorResult(
        targetClass = RotacaoDTO::class,
        columns = arrayOf(
            ColumnResult(name = "id"),
            ColumnResult(name = "sernsortrack_dispositivo_rotacao_id"),
            ColumnResult(name = "veiculos_id"),
            ColumnResult(name = "momento"),
            ColumnResult(name = "rpm"),
        )
    )]
)

@Entity
@Table(name = "sensortrack_rotacoes")
data class RotacaoEntity (
    @Id
    val id : UUID,
    @OneToOne
    @JoinColumn(name = "sensortrack_dispositivo_rotacao_id")
    val dispositivo : DispositivoEntity,
    @OneToOne
    @JoinColumn(name = "veiculo_id")
    val veiculo : VeiculoEntity?,
    val momento : Date,
    val rpm : Int
){
}