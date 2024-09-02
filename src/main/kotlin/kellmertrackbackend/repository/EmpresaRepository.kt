package kellmertrackbackend.repository

import kellmertrackbackend.model.dto.DispositivoListDTO
import kellmertrackbackend.model.dto.EmpresaDTO
import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.entities.EmpresaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EmpresaRepository : JpaRepository<EmpresaEntity, String> {

    @Query(
        "select new kellmertrackbackend.model.dto.EmpresaDTO(" +
                " e.codigo, e.nome, e.latitude, e.longitude, e.raio" +
                ") from EmpresaEntity e" +
                " where (e.codigo like upper(concat(:codigo, '%')) or :codigo = '')" +
                " and (e.nome like upper(concat('%',:descricao, '%')) or :descricao = '')" +
                " order by e.codigo"
    )
    fun pesquisaEmpresa(
        @Param("codigo") codigo: String,
        @Param("descricao") descricao: String
    ): List<EmpresaDTO>

    fun findByCodigo(codigo : String?) : EmpresaEntity?

    @Query(
        "select new kellmertrackbackend.model.dto.EmpresaDTO( "+
        " e.codigo, e.nome, e.latitude, e.longitude, e.raio) from EmpresaEntity e"
    )
    fun getEmpresas(): List<EmpresaDTO>

    @Query(
        "select new kellmertrackbackend.model.dto.PesquisaDTO(" +
                " codigo, nome" +
                ") from EmpresaEntity "+
                "order by codigo"
    )
    fun pesquisaEmpresaCombobox(): List<PesquisaDTO>?
}