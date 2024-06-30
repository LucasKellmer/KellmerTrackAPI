package kellmertrackbackend.model.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "entregas_consumo")
data class EntregaConsumoEntity(
    @Id
    val id : UUID,
    val momento : Date?,
    @ManyToOne
    @JoinColumn(name = "entrega_id")
    val entrega : EntregaEntity?,
    @ManyToOne
    @JoinColumn(name = "veiculo")
    val veiculo : VeiculoEntity?,
    @Column(name = "momento_consumo")
    val momentoConsumo: Date?
) {
}