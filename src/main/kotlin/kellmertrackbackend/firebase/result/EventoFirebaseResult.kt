package kellmertrackbackend.firebase.result

import com.google.cloud.firestore.DocumentChange
import com.google.cloud.firestore.QueryDocumentSnapshot
import kellmertrackbackend.model.dto.EventoDTO
import java.util.*

class EventoFirebaseResult : FirebaseResult<EventoDTO>{

    override fun parse(doc: QueryDocumentSnapshot): EventoDTO {
        val cal = Calendar.getInstance()
        cal.time = doc.getDate("momento")
        return EventoDTO(
            id = doc.getString("id")!!,
            entregaId = doc.getString("entregaId")?.toInt()!!,
            momento = cal.time,
            latitude = doc.getDouble("latitude"),
            longitude = doc.getDouble("longitude"),
            veiculo = doc.getString("veiculo")!!,
            tipo = doc.getString("tipo")!!
        )
    }

    override fun getResult(query: List<DocumentChange>): List<EventoDTO> {
        val list = mutableListOf<EventoDTO>()
        query.forEach { evento ->
            list.add(parse(evento.document))
        }
        return list
    }
}