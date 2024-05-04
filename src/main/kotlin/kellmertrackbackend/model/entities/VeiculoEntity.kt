package kellmertrackbackend.model.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "veiculos")
data class VeiculoEntity(
    @Id
    val identificacao: String,
    val descricao : String,
) {
}