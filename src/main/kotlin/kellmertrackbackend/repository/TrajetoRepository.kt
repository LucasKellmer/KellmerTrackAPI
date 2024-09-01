package kellmertrackbackend.repository

import kellmertrackbackend.model.dto.TrajetoDiarioListDTO
import kellmertrackbackend.model.entities.TrajetoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TrajetoRepository : JpaRepository<TrajetoEntity, UUID> {

    @Query(
        "select distinct on (veiculo_id) veiculo_id from trajetos ", nativeQuery = true
                //"where date(momento) >= current_date - 2", nativeQuery = true
    )
    fun buscaVeiculosTrajetos() : List<String>

    @Query(
        "select * from trajetos\n" +
                "where veiculo_id = :veiculo\n"+
                "order by momento desc limit 1", nativeQuery = true
    )
    fun buscaUltimaLocalizacao(veiculo : String): TrajetoEntity?

    @Query("select distinct veiculo_id from trajetos where cast(momento as date) = :data", nativeQuery = true)
    fun buscaVeiculosTrajeto(data: Date) : List<String>

    fun findByVeiculoIdentificacaoAndMomentoBetweenOrderByMomento(veiculo: String, dataIni: Date, dataFim: Date): List<TrajetoEntity>?
}