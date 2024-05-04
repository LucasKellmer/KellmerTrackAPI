package kellmertrackbackend.repository

import jakarta.transaction.Transactional
import kellmertrackbackend.model.entities.EntregaConsumoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EntregasConsumoRepository : JpaRepository<EntregaConsumoEntity, Int>{

    @Query("select * from entregas_consumo where momento_consumo is null", nativeQuery = true)
    fun findEntregasNaoConsumidas() : List<EntregaConsumoEntity?>

    @Transactional
    @Modifying
    @Query(value = "update entregas_consumo set momento_consumo = current_timestamp where entrega_id = :id", nativeQuery = true)
    fun atualizaMomentoConsumo(id: Int)
}