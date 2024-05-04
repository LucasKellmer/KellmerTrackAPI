package kellmertrackbackend.service

import kellmertrackbackend.model.dto.PesquisaDTO
import kellmertrackbackend.repository.ObraRepository
import org.springframework.stereotype.Service

@Service
class ObraService(
    private val obraRepository: ObraRepository
) {

    fun pesquisaObraCombobox(): List<PesquisaDTO>? {
        return obraRepository.pesquisaObraCombobox()
    }
}