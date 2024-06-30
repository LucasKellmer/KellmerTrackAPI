package kellmertrackbackend.model.entities

import jakarta.persistence.*

@Entity
@Table(name = "contratos")
data class ContratoEntity(
    @Id
    val numero: String,
    @ManyToOne
    @JoinColumn(name = "empresa")
    val empresa : EmpresaEntity,
    @ManyToOne
    @JoinColumn(name = "obra_id")
    val obraId : ObraEntity,
    @ManyToOne
    @JoinColumn(name = "cliente")
    val cliente: ClienteEntity,
){
}