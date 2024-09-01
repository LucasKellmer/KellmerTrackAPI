package kellmertrackbackend.model.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "trajetos")
data class TrajetoEntity (
    @Id
    val id : UUID,
    @ManyToOne
    @JoinColumn(name = "dispositivo")
    val dispositivo : DispositivoEntity,
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    val veiculo : VeiculoEntity?,
    val momento : Date,
    val latitude : Double,
    val longitude : Double,
    val velocidade : Int
){
}