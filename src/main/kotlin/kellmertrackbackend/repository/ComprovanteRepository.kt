package kellmertrackbackend.repository

import kellmertrackbackend.model.entities.ComprovanteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ComprovanteRepository : JpaRepository<ComprovanteEntity, String>{
}