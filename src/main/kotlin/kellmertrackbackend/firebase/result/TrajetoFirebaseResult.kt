package kellmertrackbackend.firebase.result

import kellmertrackbackend.model.dto.TrajetoDTO
import com.google.cloud.firestore.DocumentChange
import com.google.cloud.firestore.QueryDocumentSnapshot
import java.util.*

class TrajetoFirebaseResult : FirebaseResult<TrajetoDTO> {

    override fun parse(doc: QueryDocumentSnapshot): TrajetoDTO {
        val cal = Calendar.getInstance()
        cal.time = doc.getDate("momento")
        return TrajetoDTO(
            id = doc.getString("id")!!,
            latitude = doc.getDouble("latitude")!!,
            longitude = doc.getDouble("longitude")!!,
            velocidade = doc.getLong("velocidade")?.toInt()!!,
            momento = cal.time,
            dispositivoId = doc.getLong("dispositivoId")?.toInt()!!, // MUDAR PARA DISPOSITIVO ID AQUI E NA ENTITY DO APLICATIVO
            veiculoId = doc.getString("veiculoId")!!
        )
    }

    override fun getResult(query: List<DocumentChange>): List<TrajetoDTO> {
        val list = mutableListOf<TrajetoDTO>()
        query.forEach { localizacao ->
            list.add(parse(localizacao.document))
        }
        return list
    }
}