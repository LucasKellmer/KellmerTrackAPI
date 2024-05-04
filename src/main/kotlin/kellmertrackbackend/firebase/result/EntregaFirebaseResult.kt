package kellmertrackbackend.firebase.result

import com.google.cloud.firestore.DocumentChange
import com.google.cloud.firestore.QueryDocumentSnapshot
import kellmertrackbackend.model.dto.EntregaFirebaseDTO
import java.util.*

class EntregaFirebaseResult : FirebaseResult<EntregaFirebaseDTO>{

    override fun parse(doc: QueryDocumentSnapshot): EntregaFirebaseDTO {
        val cal = Calendar.getInstance()
        cal.time = doc.getDate("momento")
        return EntregaFirebaseDTO(
            id = doc.getLong("id")!!.toInt(),
            momento = cal.time,
            obraId = doc.getLong("obraId")!!.toInt(),
            status = doc.getLong("status")!!.toInt(),
            veiculo = doc.getString("veiculo").toString(),
            quantidade = doc.getLong("quantidade")!!.toInt(),
            dataEntradaUsina = doc.getDate("dataEntradaUsina"),
            dataSaidaUsina = doc.getDate("dataSaidaUsina"),
            dataEntradaObra = doc.getDate("dataEntradaObra"),
            dataSaidaObra = doc.getDate("dataSaidaObra"),
            sincronizado = doc.getBoolean("sincronizado"),
        )
    }

    override fun getResult(query: List<DocumentChange>): List<EntregaFirebaseDTO> {
        val list = mutableListOf<EntregaFirebaseDTO>()
        query.forEach { entrega ->
            list.add(parse(entrega.document))
        }
        return list
    }
}