package kellmertrackbackend.model.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import java.util.Date
import java.util.UUID

@Entity(name = "eventos")
data class EventoEntity(
    @Id
    val id : String,
    @OneToOne
    @JoinColumn(name = "entrega_id")
    val entregaId : EntregaEntity?,
    val momento : Date,
    val latitude : Double?,
    val longitude : Double?,
    @OneToOne
    @JoinColumn(name = "veiculo")
    val veiculo : VeiculoEntity?,
    val tipo : String
) {
}