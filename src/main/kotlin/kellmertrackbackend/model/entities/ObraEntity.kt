package kellmertrackbackend.model.entities

import jakarta.persistence.*

@Entity
@Table(name = "obras")
data class ObraEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null,
    val descricao : String,
    val cidade : String,
    val bairro : String,
    val numero : Int,
    val complemento : String?,
    val latitude : Double,
    val longitude : Double,
    val raio : Double
)