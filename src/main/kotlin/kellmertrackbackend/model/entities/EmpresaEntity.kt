package kellmertrackbackend.model.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "usinas")
data class EmpresaEntity(
    @Id
    val codigo : String,
    val nome : String,
    val latitude : Double,
    val longitude : Double,
    val raio : Double
) {
}