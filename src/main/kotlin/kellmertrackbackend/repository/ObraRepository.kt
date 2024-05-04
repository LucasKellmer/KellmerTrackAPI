package kellmertrackbackend.repository

import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.entities.ObraEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ObraRepository : JpaRepository<ObraEntity, Int>{

    @Query(
        "select new kellmertrackbackend.model.dto.PesquisaDTO(" +
                " id, descricao" +
                ") from ObraEntity "+
                "order by descricao"
    )
    fun pesquisaObraCombobox(): List<PesquisaDTO>?
}