package kellmertrackbackend.repository

import kellmertrackbackend.model.dto.MotoristaDTO
import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.entities.MotoristaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MotoristaRepository : JpaRepository<MotoristaEntity, Int> {

    @Query(
        "select new kellmertrackbackend.model.dto.PesquisaDTO(" +
                " id, nome) from MotoristaEntity "+
                "order by nome"
    )
    fun pesquisaMotoristaCombobox(): List<PesquisaDTO>?

    @Query(
        "select new kellmertrackbackend.model.dto.MotoristaDTO(" +
                " m.id, m.nome" +
                ") from MotoristaEntity m" +
                " where (m.nome like upper(concat(:nome, '%')) or :nome = '')" +
                " order by m.id asc"
    )
    fun pesquisaMotoristas(
        @Param("nome") nome: String,
    ): List<MotoristaDTO>

    @Query(
        "select nextval('motoristas_id_seq')", nativeQuery = true
    )
    fun nextId(): Int

}