package kellmertrackbackend.model.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.Date
import java.util.UUID

@Entity
@Table(name = "eventos")
data class EventoEntity(
    @Id
    val id : String,
    @ManyToOne
    @JoinColumn(name = "entrega_id")
    val entregaId : EntregaEntity?,
    @ManyToOne
    @JoinColumn(name = "contrato")
    val contrato : ContratoEntity?,
    val momento : Date,
    val latitude : Double?,
    val longitude : Double?,
    @ManyToOne
    @JoinColumn(name = "veiculo")
    val veiculo : VeiculoEntity?,
    val tipo : String
) {
}