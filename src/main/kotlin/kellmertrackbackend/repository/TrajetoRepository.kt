package kellmertrackbackend.repository

import kellmertrackbackend.model.entities.TrajetoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TrajetoRepository : JpaRepository<TrajetoEntity, UUID> {
}