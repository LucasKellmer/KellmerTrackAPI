package kellmertrackbackend.model.entities

import jakarta.persistence.*
import kellmertrackbackend.model.constants.DispositivoModelo
import java.util.*

@Entity
@Table(name = "dispositivos")
class DispositivoEntity (
    @Id
    val numeroInterno : String,
    val mac : String,
    val modelo : String?,
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    val veiculo : VeiculoEntity,
    val dataVinculo : Date?,
    @ManyToOne
    @JoinColumn(name = "motorista_id", nullable = true)
    val motorista : MotoristaEntity,
    @ManyToOne
    @JoinColumn(name = "empresa")
    val empresa : EmpresaEntity?,
)

