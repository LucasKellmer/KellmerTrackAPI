package kellmertrackbackend.service

import kellmertrackbackend.model.constants.EntregaStatus
import kellmertrackbackend.model.dto.*
import kellmertrackbackend.model.entities.EntregaEntity
import kellmertrackbackend.model.entities.mapper.EntregaMapper
import kellmertrackbackend.repository.EntregaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.Date

@Service
class EntregaService(
    private val entregaRepository: EntregaRepository,
    private val comprovanteService : ComprovanteService,
    private val entregaMapper: EntregaMapper
) {

    //aplicativo
    fun atualizaEntregaStatus(id: Int, status : Int){
        entregaRepository.atualizaEntregaStatus(id, status)
    }

    fun salvaComprovante(comprovante : ComprovanteDTO){
        comprovanteService.salvaComprovante(comprovante)
    }

    //firebase
    fun salvaEntrega(entrega : EntregaFirebaseDTO){
        val entregaEntity = entregaMapper.fromEntregaFirebaseDTOtoEntity(entrega)
        println("================== salvaEntrega chamado:")
        println(entregaEntity)
        entregaRepository.save(entregaEntity)
    }

    //front ent
    fun pesquisaEntrega(descricao: String, dataIni : Date, dataFim : Date, status: Int?): List<EntregaListDTO> {
        val entregaStatus = when (status) {
            0 -> EntregaStatus.PENDENTE
            1 -> EntregaStatus.TRANSITO
            2 -> EntregaStatus.ENTREGUE
            3 -> EntregaStatus.FINALIZADO
            else -> null
        }
        return entregaRepository.pesquisaEntregas(descricao, dataIni, dataFim, entregaStatus)
    }

    fun findEntregaById(id: Int): EntregaDTO? {
        return entregaMapper.toEntregaDTO(entregaRepository.findByIdOrNull(id))
    }

    fun salvaEntrega(entrega: EntregaFormDTO): EntregaEntity {
        val entregaEntity = entregaMapper.toEntregaEntity(entrega)
        try {
            return entregaRepository.save(entregaEntity)
        } catch (e: Exception) {
            throw Exception("Ocorreu um erro ao salvar a entrega! ${e.message}")
        }

    }

    fun deleteEntrega(id : Int){
        try {
            return entregaRepository.deleteById(id)
        }catch (e : Exception){
            throw Exception("Erro ao excluir dispositivo: ${e.message}")
        }
    }
}