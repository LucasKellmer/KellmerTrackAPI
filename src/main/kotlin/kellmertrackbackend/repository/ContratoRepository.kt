package kellmertrackbackend.repository

import kellmertrackbackend.model.dto.ClienteDTO
import kellmertrackbackend.model.dto.ContratoListDTO
import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.entities.ContratoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ContratoRepository : JpaRepository<ContratoEntity, String>{

    @Query(
        "select new kellmertrackbackend.model.dto.ContratoListDTO(" +
                " c.numero, c.obraId.descricao, c.empresa.nome, c.cliente.nome" +
                ") from ContratoEntity c" +
                " where (c.numero = :numero or : numero = '999')" +
                " and (c.empresa.codigo like upper(concat(:empresa, '%')) or :empresa = '')" +
                " and (c.cliente.nome like upper(concat(:cliente, '%')) or :cliente = '')" +
                " order by c.numero"
    )
    fun pesquisaContrato(
        @Param("numero") numero: String,
        @Param("empresa") empresa: String,
        @Param("cliente") cliente: String,
    ): List<ContratoListDTO>

    fun findByNumero(numero : String) : ContratoEntity?

    @Query(
        "select new kellmertrackbackend.model.dto.PesquisaDTO(" +
                " numero, numero" +
                ") from ContratoEntity "+
                "order by numero"
    )
    fun pesquisaContratoCombobox(): List<PesquisaDTO>?
}