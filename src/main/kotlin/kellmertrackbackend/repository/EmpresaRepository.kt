package kellmertrackbackend.repository

import kellmertrackbackend.model.dto.EmpresaDTO
import kellmertrackbackend.model.entities.EmpresaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EmpresaRepository : JpaRepository<EmpresaEntity, String> {

    fun findByCodigo(codigo : String?) : EmpresaEntity?

    @Query(
        "select new kellmertrackbackend.model.dto.EmpresaDTO( "+
        " e.codigo, e.nome, e.latitude, e.longitude, e.raio) from EmpresaEntity e"
    )
    fun getEmpresas(): List<EmpresaDTO>
}