package kellmertrackbackend.repository

import kellmertrackbackend.model.entities.ContratoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContratoRepository : JpaRepository<ContratoEntity, String>{
}