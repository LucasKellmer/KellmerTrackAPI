package kellmertrackbackend.repository

import kellmertrackbackend.model.dto.ClienteDTO
import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.model.entities.ClienteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ClienteRepository : JpaRepository<ClienteEntity, String>{

    @Query(
        "select new kellmertrackbackend.model.dto.ClienteDTO(" +
                " c.id, c.nome, c.cpf, c.cnpj, c.email" +
                ") from ClienteEntity c" +
                " where (c.nome like upper(concat(:nome, '%')) or :nome = '')" +
                " order by c.nome"
    )
    fun pesquisaCliente(
        @Param("nome") descricao: String,
    ): List<ClienteDTO>

    fun findById(id : Int?) : ClienteEntity?

    @Query(
        "select new kellmertrackbackend.model.dto.PesquisaDTO(" +
                " id, nome" +
                ") from ClienteEntity "+
                "order by nome"
    )
    fun pesquisaClienteCombobox(): List<PesquisaDTO>?
}