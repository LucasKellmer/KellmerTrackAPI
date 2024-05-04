package kellmertrackbackend.service

import kellmertrackbackend.repository.EntregasConsumoRepository
import org.springframework.stereotype.Service

@Service
class EntregasConsumoService(
    private val entregasConsumoRepository: EntregasConsumoRepository
) {
    fun atualizaMomentoConsumo(id : Int){
        println("Atualizando momento consumo para a entrega: $id")
        entregasConsumoRepository.atualizaMomentoConsumo(id)
    }
}