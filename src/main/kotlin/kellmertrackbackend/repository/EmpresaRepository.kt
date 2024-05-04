package kellmertrackbackend.repository

import kellmertrackbackend.model.entities.EmpresaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmpresaRepository : JpaRepository<EmpresaEntity, String> {

    fun findByCodigo(codigo : String) : EmpresaEntity?
}