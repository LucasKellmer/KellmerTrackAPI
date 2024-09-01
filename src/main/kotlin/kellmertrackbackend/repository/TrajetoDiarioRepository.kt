package kellmertrackbackend.repository

import kellmertrackbackend.model.dto.TrajetoDiarioListDTO
import kellmertrackbackend.model.entities.TrajetoDiarioEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TrajetoDiarioRepository : JpaRepository<TrajetoDiarioEntity, Int>{
    @Query("select new kellmertrackbackend.model.dto.TrajetoDiarioListDTO(" +
            " t.id, t.data, t.veiculo) from TrajetoDiarioEntity t"+
            " where t.data between :dataIni and :dataFim"+
            " and t.veiculo like concat('%',:veiculo,'%')")
    fun buscaTrajetosDiarios(veiculo: String, dataIni: Date, dataFim: Date): List<TrajetoDiarioListDTO>
}