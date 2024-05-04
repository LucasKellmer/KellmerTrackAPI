package kellmertrackbackend.repository

import kellmertrackbackend.model.entities.AlvaraConstrucaoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AlvaraConstrucaoRepository : JpaRepository<AlvaraConstrucaoEntity, Int> {

    @Query(
        "select * from alvara_construcao where numero_alvara = :numeroAlvara", nativeQuery = true
    )
    fun findByNumeroAlvara(numeroAlvara : String) : AlvaraConstrucaoEntity?
}
