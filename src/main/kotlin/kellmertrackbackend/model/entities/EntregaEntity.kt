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
    @OneToOne
    @JoinColumn(name = "veiculo", nullable = false)
    val veiculo : VeiculoEntity?,
    @OneToOne
    @JoinColumn(name = "obra_id", nullable = false)
    val obra : ObraEntity?,
    val status : EntregaStatus?,
    val quantidade : Double,
    val dataChegadaUsina : Date? = null,
    val dataSaidaUsina : Date? = null,
    val dataChegadaObra : Date? = null,
    val dataSaidaObra : Date? = null,
) {
}