package kellmertrackbackend.model.entities

import jakarta.persistence.*

@Entity
@Table(name = "clientes")
data class ClienteEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val nome: String,
    val cpf: String,
    val cnpj: String?,
    val email: String?,
    ) {
}