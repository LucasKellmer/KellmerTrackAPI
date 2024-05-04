package kellmertrackbackend.repository

import kellmertrackbackend.model.entities.EventoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventoRepository : JpaRepository<EventoEntity, Int>{
}