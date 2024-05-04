package kellmertrackbackend.repository

import kellmertrackbackend.model.dto.DispositivoDTO
import kellmertrackbackend.model.dto.MotoristaDTO
import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.dto.VeiculoDTO
import kellmertrackbackend.model.entities.VeiculoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface VeiculoRepository : JpaRepository<VeiculoEntity, String>{

    //front end e aplicativo
    fun findByIdentificacao(identificacao : String?) : VeiculoEntity?

    //front end

    @Query(
        "select new kellmertrackbackend.model.dto.PesquisaDTO(" +
                " identificacao, descricao)"+
                " from VeiculoEntity "
    )
    fun pesquisaVeiculosCombobox() : List<PesquisaDTO>

    @Query(
        "select new kellmertrackbackend.model.dto.VeiculoDTO(" +
                " v.identificacao, v.descricao" +
                ") from VeiculoEntity v" +
                " where (v.identificacao like upper(concat(:identificacao, '%')) or :identificacao = '')" +
                " and (v.descricao like upper(concat(:descricao, '%')) or :descricao = '')" +
                " order by v.identificacao asc"
    )
    fun pesquisaVeiculos(
        @Param("identificacao") identificacao: String,
        @Param("descricao") descricao : String,
    ): List<VeiculoDTO>

}