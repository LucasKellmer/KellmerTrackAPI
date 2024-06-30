package kellmertrackbackend.repository

import kellmertrackbackend.model.entities.ClienteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClienteRepository : JpaRepository<ClienteEntity, String>{
}