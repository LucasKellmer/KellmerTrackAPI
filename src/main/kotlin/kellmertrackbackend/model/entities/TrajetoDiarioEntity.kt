package kellmertrackbackend.model.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "trajetos_diarios")
data class TrajetoDiarioEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null,
    val data : Date,
    val veiculo : String,
    @Column(name = "trajeto_zip")
    val trajetoZip : ByteArray
) {
}