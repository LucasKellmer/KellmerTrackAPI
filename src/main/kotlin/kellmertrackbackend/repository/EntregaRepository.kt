package kellmertrackbackend.repository

import jakarta.transaction.Transactional
import kellmertrackbackend.model.constants.EntregaStatus
import kellmertrackbackend.model.dto.EntregaListDTO
import kellmertrackbackend.model.entities.EntregaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EntregaRepository : JpaRepository<EntregaEntity, Int> {

    //aplicativo
    /*@Query(
        "select * from entregas where veiculo = :veiculo and status = 0", nativeQuery = true
    )
    fun findEntregaByVeiculo(veiculo : String?) : EntregaEntity?*/

    @Transactional
    @Modifying
    @Query(value = "update entregas set status = :status where id = :id", nativeQuery = true)
    fun atualizaEntregaStatus(id: Int, status: Int)

    //front end
    @Query(
        "select new kellmertrackbackend.model.dto.EntregaListDTO(" +
                "e.id, e.momento,e.veiculo.identificacao, e.obra.id, e.obra.descricao, e.status, e.quantidade"+
                ") from EntregaEntity e " +
                " where e.momento between :dataIni and :dataFim " +
                " and e.obra.descricao like upper(concat('%',:descricao, '%'))" +
                " and (e.status = :status or :status is null)"+
                " order by e.obra.descricao asc"
    )
    fun pesquisaEntregas(
        @Param("descricao") descricao: String,
        @Param("dataIni") dataIni: Date,
        @Param("dataFim") dataFim: Date,
        @Param("status") status: EntregaStatus?,
    ): List<EntregaListDTO>

    @Query(
        "select nextval('entregas_id_seq')", nativeQuery = true
    )
    fun nextId(): Int
}