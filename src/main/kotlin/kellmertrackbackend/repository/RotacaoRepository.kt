package kellmertrackbackend.repository

import kellmertrackbackend.model.dto.RotacaoDTO
import kellmertrackbackend.model.entities.RotacaoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RotacaoRepository : JpaRepository<RotacaoEntity, UUID> {

    @Query(
        "select new kellmertrackbackend.model.dto.RotacaoDTO(" +
            " r.id, r.dispositivo.numeroInterno, r.veiculo.identificacao, \n"+
            " r.momento, r.rpm, r.entregaId.id, r.bateria, r.temperatura, r.direcao\n"+
            ") from RotacaoEntity r \n"+
            " where r.veiculo.identificacao = :identificacao \n"+
            " order by r.momento desc limit 1"
    )
    //@Query(name = "buscaUltimaRotacao", nativeQuery = true)
    fun buscaUltimaRotacao(identificacao: String): RotacaoDTO?

    @Query(
        "SELECT id, dispositivo, veiculo, momento, rpm, entrega_id, bateria, temperatura, direcao \n" +
                "FROM ( \n" +
                "    SELECT *, LAG(rpm) OVER (ORDER BY momento) AS rpm_anterior \n" +
                "    FROM rotacoes \n" +
                "    WHERE entrega_id = :entregaId \n" +
                ") AS sub \n" +
                "WHERE rpm_anterior IS NULL OR rpm <> rpm_anterior \n" +
                "ORDER BY momento", nativeQuery = true
    )
    fun buscaRotacaoByEntrega(entregaId: Int): List<RotacaoEntity>?

    @Query("select new kellmertrackbackend.model.dto.RotacaoDTO(" +
            " r.id, r.dispositivo.numeroInterno, r.veiculo.identificacao, r.momento, r.rpm, r.entregaId.id," +
            " r.bateria, r.temperatura, r.direcao) from RotacaoEntity r"+
            " where r.momento between :dataIni and :dataFim and r.veiculo.identificacao = :veiculo"+
            " order by r.momento"
    )
    fun buscaRotacaoByData(dataIni:Date, dataFim:Date, veiculo: String) : List<RotacaoDTO>
}