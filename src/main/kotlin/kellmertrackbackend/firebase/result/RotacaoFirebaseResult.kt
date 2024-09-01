package kellmertrackbackend.firebase.result

import kellmertrackbackend.model.dto.RotacaoDTO
import com.google.cloud.firestore.DocumentChange
import com.google.cloud.firestore.QueryDocumentSnapshot
import java.util.*

class RotacaoFirebaseResult : FirebaseResult<RotacaoDTO>{

    override fun parse(doc: QueryDocumentSnapshot): RotacaoDTO {
        println(doc.id)
        println(doc.data)
        val cal = Calendar.getInstance()
        cal.time = doc.getDate("momento")
        return RotacaoDTO(
            id = doc.getString("id")!!,
            rpm = doc.getLong("rpm")?.toInt()!!,
            momento = cal.time,
            veiculo = doc.getString("veiculo")!!,
            dispositivo = doc.getString("dispositivo")!!,
            entregaId = doc.getLong("entregaId")?.toInt(),
            bateria = doc.getLong("bateria")?.toInt(),
            temperatura = doc.getLong("temperatura")?.toInt(),
            direcao = doc.getLong("direcao")?.toInt(),
        )
    }

    override fun getResult(query: List<DocumentChange>): List<RotacaoDTO> {
        val list = mutableListOf<RotacaoDTO>()
        query.forEach { rotacao ->
            list.add(parse(rotacao.document))
        }
        return list
    }
}