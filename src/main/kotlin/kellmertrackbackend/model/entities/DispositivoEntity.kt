package kellmertrackbackend.model.entities

import jakarta.persistence.*
import kellmertrackbackend.model.constants.DispositivoModelo
import java.util.*

@Entity
@Table(name = "track_dispositivos_rotacao")
class DispositivoEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int,
    val numeroInterno : String,
    val mac : String,
    val modelo : DispositivoModelo,
    @OneToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    val veiculo : VeiculoEntity,
    val dataVinculo : Date?,
    @OneToOne
    @JoinColumn(name = "motorista_id", nullable = true)
    val motorista : MotoristaEntity,
    @OneToOne
    @JoinColumn(name = "empresa")
    val empresa : EmpresaEntity?,
)

