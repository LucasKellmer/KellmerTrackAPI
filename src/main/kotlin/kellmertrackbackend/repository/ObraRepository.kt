package kellmertrackbackend.repository

import kellmertrackbackend.model.dto.ObraDTO
import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.entities.ObraEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ObraRepository : JpaRepository<ObraEntity, Int>{

    @Query(
        "select new kellmertrackbackend.model.dto.ObraDTO(" +
                " o.id, o.descricao, o.cidade, o.bairro, o.numero, o.complemento, o.latitude, o.longitude, o.raio" +
                ") from ObraEntity o" +
                " where (o.descricao like upper(concat(:descricao, '%')) or :descricao = '')" +
                " and (o.cidade like upper(concat('%',:cidade, '%')) or :cidade = '')" +
                " order by o.id"
    )
    fun pesquisaObra(
        @Param("descricao") descricao: String,
        @Param("cidade") cidade: String,
    ): List<ObraDTO>

    fun findById(id : Int?) : ObraEntity?

    @Query(
        "select new kellmertrackbackend.model.dto.PesquisaDTO(" +
                " id, descricao" +
                ") from ObraEntity "+
                "order by descricao"
    )
    fun pesquisaObraCombobox(): List<PesquisaDTO>?
}