package kellmertrackbackend.firebase.result

import com.google.cloud.firestore.DocumentChange
import com.google.cloud.firestore.QueryDocumentSnapshot

interface FirebaseResult<T> {
    fun parse(doc: QueryDocumentSnapshot) : T
    fun getResult(query: List<DocumentChange>) : List<T>
}