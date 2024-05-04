package kellmertrackbackend.firebase.result

import kellmertrackbackend.model.dto.RotacaoDTO
import com.google.cloud.firestore.DocumentChange
import com.google.cloud.firestore.QueryDocumentSnapshot
import java.util.*

class RotacaoFirebaseResult : FirebaseResult<RotacaoDTO>{

    override fun parse(doc: QueryDocumentSnapshot): RotacaoDTO {
        val cal = Calendar.getInstance()
        cal.time = doc.getDate("momento")
        return RotacaoDTO(
            id = doc.getString("id")!!,
            rpm = doc.getLong("rpm")?.toInt()!!,
            momento = cal.time,
            veiculoId = doc.getString("veiculoId")!!,
            dispositivoId = doc.getLong("dispositivoId")?.toInt()!!
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