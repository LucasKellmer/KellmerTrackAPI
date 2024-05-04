package kellmertrackbackend.repository

import jakarta.transaction.Transactional
import kellmertrackbackend.model.dto.DispositivoDTO
import kellmertrackbackend.model.dto.DispositivoListDTO
import kellmertrackbackend.model.entities.DispositivoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DispositivoRepository : JpaRepository<DispositivoEntity, Int> {

    //aplicativo
    fun findDispositivoById(id: Int): DispositivoEntity

    fun findByNumeroInterno(numeroInterno : String?) : DispositivoEntity?

    fun findByMac(mac : String?) : DispositivoEntity?

    fun findByVeiculoIdentificacao(veiculo: String?) : DispositivoEntity?

    fun findByMotoristaId(motoristaId: Int?) : DispositivoEntity?

    @Modifying
    @Transactional
    @Query(value = "update track_dispositivos_rotacao set data_vinculo = current_date where numero_interno = :numeroInterno", nativeQuery = true)
    fun atualizaDataVinculo(numeroInterno: String?)

    //front end
    @Query(
        "select new kellmertrackbackend.model.dto.DispositivoListDTO(" +
                " d.id, d.numeroInterno, d.mac, d.modelo, d.veiculo.identificacao, d.dataVinculo, d.motorista.id,  d.motorista.nome, d.empresa.codigo" +
                ") from DispositivoEntity d" +
                " where (d.veiculo.identificacao = :veiculoId or : veiculoId = 'todos')" +
                " and (d.numeroInterno like upper(concat(:numeroInterno, '%')) or : numeroInterno = '')" +
                " and (d.mac like upper(concat('%',:mac, '%')) or : mac = '')" +
                " and (d.motorista.id = :motoristaId or : motoristaId = 999)" +
                " order by d.id asc"
    )
    fun pesquisaDispositivos(
        @Param("numeroInterno") numeroInterno: String,
        @Param("motoristaId") motoristaId: Int,
        @Param("veiculoId") veiculoId: String,
        @Param("mac") mac : String
    ): List<DispositivoListDTO>

    @Query(
        "select nextval('track_dispositivos_rotacao_id_seq')", nativeQuery = true
    )
    fun nextId(): Int

    @Modifying
    @Query(value = "update track_dispositivos_rotacao set data_vinculo = null where id = :id", nativeQuery = true)
    fun limpaDataVinculo(id: Int)


}