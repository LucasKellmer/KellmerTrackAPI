package kellmertrackbackend.model.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "sensortrack_trajetos")
data class TrajetoEntity (
    @Id
    val id : UUID,
    @ManyToOne
    @JoinColumn(name = "dispositivo_id")
    val dispositivoId : DispositivoEntity,
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    val veiculos : VeiculoEntity?,
    val momento : Date,
    val latitude : Double,
    val longitude : Double,
    val velocidade : Int
){
}