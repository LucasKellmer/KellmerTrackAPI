package kellmertrackbackend.model.entities

import jakarta.persistence.*
import kellmertrackbackend.model.constants.EntregaStatus
import java.util.*

@Entity
@Table(name = "entregas")
data class EntregaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int,
    val momento : Date,
    @ManyToOne
    @JoinColumn(name = "veiculo", nullable = false)
    val veiculo : VeiculoEntity,
    @ManyToOne
    @JoinColumn(name = "contrato", nullable = false)
    val contrato : ContratoEntity,
    val status : EntregaStatus?,
    val quantidade : Double,
    val quantidadeEntregue : Double? = null,
    val dataChegadaUsina : Date? = null,
    val dataSaidaUsina : Date? = null,
    val dataChegadaObra : Date? = null,
    val dataSaidaObra : Date? = null,
) {
}