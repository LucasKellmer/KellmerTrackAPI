package kellmertrackbackend.service

import kellmertrackbackend.model.dto.TrajetoDTO
import kellmertrackbackend.model.dto.TrajetoDiarioDTO
import kellmertrackbackend.model.dto.TrajetoDiarioListDTO
import kellmertrackbackend.model.entities.TrajetoEntity
import kellmertrackbackend.model.entities.mapper.TrajetoDiarioMapper
import kellmertrackbackend.model.entities.mapper.TrajetoMapper
import kellmertrackbackend.repository.RotacaoRepository
import kellmertrackbackend.repository.TrajetoDiarioRepository
import kellmertrackbackend.repository.TrajetoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TrajetoService(
    private val trajetoDiarioRepository: TrajetoDiarioRepository,
    private val trajetoDiarioMapper: TrajetoDiarioMapper,
    private val rotacaoRepository: RotacaoRepository,
    private val trajetoRepository: TrajetoRepository,
    private val trajetoMapper: TrajetoMapper,
) {
    //Aplicativo
    fun salvaTrajeto(trajeto: TrajetoDTO) {
        val trajetoEntity = trajetoMapper.fromDTOtoTrajetoEntity(trajeto)
        trajetoRepository.save(trajetoEntity)
    }

    fun buscaTodosTrajetos() : MutableList<TrajetoEntity> {
        return trajetoRepository.findAll()
    }

    //Front

    fun buscaTrajetos(veiculo: String, dataIni: Date, dataFim: Date) : List<TrajetoDiarioListDTO>{
        return trajetoDiarioRepository.buscaTrajetosDiarios(veiculo, dataIni, dataFim)
    }

    fun buscaTrajetoDiarioById(id: Int): TrajetoDiarioDTO?{
        return trajetoDiarioMapper.fromEntityToDTO(trajetoDiarioRepository.findById(id).get())
    }
}