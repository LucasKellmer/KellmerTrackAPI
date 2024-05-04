package kellmertrackbackend.repository

import kellmertrackbackend.model.dto.RotacaoDTO
import kellmertrackbackend.model.entities.RotacaoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RotacaoRepository : JpaRepository<RotacaoEntity, UUID> {

    //RotacaoEntity
    @Query(name = "buscaUltimaRotacao", nativeQuery = true)
    fun buscaUltimaRotacao(identificacao: String): RotacaoDTO?
}