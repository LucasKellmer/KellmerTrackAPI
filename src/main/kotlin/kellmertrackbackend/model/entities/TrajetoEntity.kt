package kellmertrackbackend.model.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "sensortrack_trajetos")
data class TrajetoEntity (
    @Id
    val id : UUID,
    @OneToOne
    @JoinColumn(name = "dispositivo_id")
    val dispositivoId : DispositivoEntity,
    @OneToOne
    @JoinColumn(name = "veiculo_id")
    val veiculos : VeiculoEntity?,
    val momento : Date,
    val latitude : Double,
    val longitude : Double,
    val velocidade : Int
){
}