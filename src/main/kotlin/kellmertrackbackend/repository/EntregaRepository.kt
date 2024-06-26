package kellmertrackbackend.repository

import jakarta.transaction.Transactional
import kellmertrackbackend.model.constants.EntregaStatus
import kellmertrackbackend.model.dto.EntregaListDTO
import kellmertrackbackend.model.dto.EntregasMonitoramentoDTO
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
    fun findById(entregaId: Int?): EntregaEntity?

    @Transactional
    @Modifying
    @Query(value = "update entregas set status = :status where id = :id", nativeQuery = true)
    fun atualizaEntregaStatus(id: Int, status: Int)

    //front end
    @Query(
        "select new kellmertrackbackend.model.dto.EntregaListDTO(" +
                "e.id, e.momento,e.veiculo.identificacao, e.contrato.obraId.id, e.contrato.obraId.descricao, e.status, e.quantidade"+
                ") from EntregaEntity e " +
                " where e.momento between :dataIni and :dataFim " +
                " and e.contrato.obraId.descricao like upper(concat('%',:descricao, '%'))" +
                " and (e.status = :status or :status is null)"+
                " order by e.contrato.obraId.descricao asc"
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

    @Query(
        "select new kellmertrackbackend.model.dto.EntregasMonitoramentoDTO("+
        " e.id, e.status, e.contrato.obraId.latitude, e.contrato.obraId.longitude, e.contrato.obraId.descricao, e.veiculo.identificacao, e.quantidade" +
            ") from EntregaEntity e" +
            //" left join ObraEntity o on o.id = e.obra.id"+
            " where e.status <> 3 "+
            " order by e.momento desc"
    )
    fun buscaEntregasMonitoramento(): List<EntregasMonitoramentoDTO>
}