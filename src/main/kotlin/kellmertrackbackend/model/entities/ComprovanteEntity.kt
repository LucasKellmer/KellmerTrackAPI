package kellmertrackbackend.model.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "entregas_comprovante")
data class ComprovanteEntity(
    @Id
    val id : String,
    @OneToOne
    @JoinColumn(name = "entrega_id")
    val entregaId : EntregaEntity,
    val recebedor : String,
    val momento : Date,
    val latitude : Double,
    val longitude : Double,
    val imgComprovante : ByteArray?
) {
}