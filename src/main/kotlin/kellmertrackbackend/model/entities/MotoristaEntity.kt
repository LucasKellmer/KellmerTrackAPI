package kellmertrackbackend.model.entities

import jakarta.persistence.*

@Entity
@Table(name = "motoristas")
data class MotoristaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int,
    var nome : String,
    /*@OneToOne
    @JoinColumn(name = "dispositivo_id", nullable = false)
    var dispositivoId : DispositivoEntity,*/
)