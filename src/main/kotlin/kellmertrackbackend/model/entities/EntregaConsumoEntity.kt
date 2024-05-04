package kellmertrackbackend.model.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "entregas_consumo")
data class EntregaConsumoEntity(
    @Id
    val id : UUID,
    val momento : Date?,
    @OneToOne
    @JoinColumn(name = "entrega_id")
    val entrega : EntregaEntity?,
    @OneToOne
    @JoinColumn(name = "veiculo")
    val veiculo : VeiculoEntity?,
    @Column(name = "momento_consumo")
    val momentoConsumo: Date?
) {
}