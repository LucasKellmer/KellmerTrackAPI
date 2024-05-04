package kellmertrackbackend.firebase

import kellmertrackbackend.exception.FirebaseException
import kellmertrackbackend.model.dto.RotacaoDTO
import kellmertrackbackend.model.dto.TrajetoDTO
import kellmertrackbackend.service.TrajetoService
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.DocumentChange
import com.google.cloud.firestore.ListenerRegistration
import com.google.cloud.firestore.Query
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import kellmertrackbackend.firebase.result.*
import kellmertrackbackend.model.dto.EntregaFirebaseDTO
import kellmertrackbackend.model.dto.EventoDTO
import kellmertrackbackend.service.EntregaService
import kellmertrackbackend.service.EventoService
import kellmertrackbackend.service.RotacaoService
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.util.*

@Component
class FirebaseDatabase (
    private val rotacaoService: RotacaoService,
    private val trajetoService: TrajetoService,
    private val entregaService: EntregaService,
    private val eventoService : EventoService,
    //private var monitoramentoService : MonitoramentoService
){

    private val log: org.slf4j.Logger = LoggerFactory.getLogger(FirebaseDatabase::class.java)
    private var listenerTrajeto: ListenerRegistration? = null
    private var listenerRotacao : ListenerRegistration? = null
    private var listnerVeiculos: ListenerRegistration? = null
    private var listnerEntregas: ListenerRegistration? = null
    //private var listnerVeiculosMonitoramento: MutableList<MonitoramentoFirebaseDTO> = mutableListOf()
    //private lateinit var monitoramentoSocketMsg: MonitoramentoSocketMsg

    fun FirebaseDatabase() {
        try {
            FirebaseApp.getApps().forEach {
                it.delete()
            }
            if (FirebaseApp.getApps().size == 0) {
                val FILE_PATH = "firebase_credentials_kellmertrack.json"
                val classPathResource = ClassPathResource(FILE_PATH)
                val credentials: GoogleCredentials = GoogleCredentials.fromStream(classPathResource.inputStream)
                val PROJECT_ID = "hobitrack-8c097"
                val options: FirebaseOptions =
                    FirebaseOptions.builder().setCredentials(credentials)
                        //.setDatabaseUrl("https://console.firebase.google.com/u/0/project/hobitrack-8c097/settings/general/android:com.example.KellmerTrack?hl=pt")
                        .setProjectId(PROJECT_ID)
                        .build()
                FirebaseApp.initializeApp(options)
            }
        } catch (e: FirebaseException) {
            e.printStackTrace()
        }
    }

    fun criaListenerMonitoramento(){
        criaListenerTrajeto()
        criaListenerRotacao()
        criaListenerEntrega()
    }

    fun criaListenerRotacao(): ListenerRegistration?{
        return try{
            log.info("Criando listener rotacao")
            val firebaseResult = RotacaoFirebaseResult()
            FirestoreClient.getFirestore()?.collection("rotacao")?.addSnapshotListener{ querySnapshot, firestoresException ->
                firestoresException?.let{
                    log.info("Exception: ${firestoresException.message}")
                    return@addSnapshotListener
                }

                if(querySnapshot != null && !querySnapshot.isEmpty) {
                    val rotacao = querySnapshot.documentChanges.filter { it.type != DocumentChange.Type.REMOVED }
                        .let { firebaseResult.getResult(it) }


                    if(!rotacao.isNullOrEmpty()){
                        atualizaRotacao(rotacao[0])
                    }
                }
            }
        }catch(e: Exception){
            log.info("Erro listner rotacao: ${e.message}")
            null
        }
    }

    fun criaListenerEntrega(): ListenerRegistration?{
        return try {
            log.info("Criando listener entregas")
            val firebaseResult = EntregaFirebaseResult()
            FirestoreClient.getFirestore()?.collection("entregas")?.addSnapshotListener{ querySnapshot, firestoresException ->
                firestoresException?.let{
                    log.info("Exception: ${firestoresException.message}")
                    return@addSnapshotListener
                }
                if(querySnapshot != null && !querySnapshot.isEmpty) {
                    val entrega = querySnapshot.documentChanges.filter { it.type != DocumentChange.Type.REMOVED }
                        .let { firebaseResult.getResult(it) }

                    if(!entrega.isNullOrEmpty()){
                        atualizaEntrega(entrega[0])
                    }
                }
            }
        } catch(e: Exception){
            log.info("Erro listner entrega: ${e.message}")
            null
        }
    }

    fun criaListenerTrajeto() : ListenerRegistration?{
        println("Criando listener trajeto")

        val firebaseResult = TrajetoFirebaseResult()
        return try {
            FirestoreClient.getFirestore()?.collection("trajetos")
                ?.whereGreaterThan("momento", Date(Date().time - 300000))
                ?.orderBy("momento", Query.Direction.DESCENDING)?.addSnapshotListener { querySnapshot, firestoreException ->
                    firestoreException?.let {
                        log.info("Exception: ${it.message}")
                        return@addSnapshotListener
                    }
                    if (querySnapshot != null && !querySnapshot.isEmpty) {
                        val localizacoes : List<TrajetoDTO> =
                            querySnapshot.documentChanges.filter { it.type != DocumentChange.Type.REMOVED }
                                .let { firebaseResult.getResult(it) }

                        if(!localizacoes.isNullOrEmpty()){
                            atualizaLocalizacao(localizacoes[0])
                        }
                    }
                }
        } catch (e: Exception) {
            log.info("Erro listener trajeto, ${e.message}")
            null
        }
    }

    private fun atualizaLocalizacao(localizacao: TrajetoDTO) {
        try {
            trajetoService.salvaTrajeto(localizacao)
            excluiTrajetoFirebase(localizacao.id)
        } catch (e: Exception) {
            log.info("Erro ao gravar trajeto ${e.message}")
        }
    }
    private fun atualizaRotacao(rotacao : RotacaoDTO){
        try {
            rotacaoService.salvaRotacao(rotacao)
            excluiRotacaoFirebase(rotacao.id)
        } catch (e:Exception){
            log.info("Erro ao gravar rotacao ${e.message}")
        }
    }

    private fun atualizaEntrega(entrega : EntregaFirebaseDTO){
        try {
            entregaService.salvaEntrega(entrega)
            //excluiEntregaFirebase(entrega.id.toString())
        }catch (e:Exception){
            log.info("Erro ao gravar entrega ${e.message}")
        }
    }

    fun excluiTrajetoFirebase(location: String) {
        try {
            FirestoreClient.getFirestore().collection("trajetos").document(location).delete()
        } catch (ex: FirebaseException) {
            throw FirebaseException("Erro ao excluir trajeto do firebase")
        }
    }

    private fun excluiRotacaoFirebase(rotacao : String){
        try{
            FirestoreClient.getFirestore().collection("rotacao").document(rotacao).delete()
        }catch (e: FirebaseException){
            throw FirebaseException("Erro ao excluir rotacao do firebase")
        }
    }

    private fun excluiEntregaFirebase(entrega : String){
        try{
            FirestoreClient.getFirestore().collection("entregas").document(entrega).delete()
        }catch (e: FirebaseException){
            throw FirebaseException("Erro ao excluir rotacao do firebase")
        }
    }

    fun excluiEventoFirebase(evento: String) {
        try {
            FirestoreClient.getFirestore().collection("eventos").document(evento).delete()
        } catch (ex: FirebaseException) {
            throw FirebaseException("Erro ao excluir eventos do firebase")
        }
    }

    /*fun setSocketMsg(monitoramentoSocketMsg: MonitoramentoSocketMsg) {
        this.monitoramentoSocketMsg = monitoramentoSocketMsg
    }*/

    fun fechaListeners() {
        listenerTrajeto?.remove()
        listenerTrajeto = null
        listenerRotacao?.remove()
        listenerRotacao = null
        listnerVeiculos?.remove()
        listnerVeiculos = null
        listnerEntregas?.remove()
        listnerEntregas = null
    }

    fun atualizaTodasLocalizacoes() {
        try {
            val firebaseResult = TrajetoFirebaseResult()
            val apiFuture = FirestoreClient.getFirestore().collection("trajetos").get()
            apiFuture.get().forEach { document ->
                gravaTodasLocalizacoes(firebaseResult.parse(document))
            }
        } catch (e: Exception) {
            log.info("Erro ao gravar todas localizações")
        }
    }

    private fun gravaTodasLocalizacoes(localizacao: TrajetoDTO){
        try{
            trajetoService.salvaTrajeto(localizacao)
            excluiTrajetoFirebase(localizacao.id)
        }catch (e:Exception){
            log.info("Erro ao gravar trajeto ${e.message}")
        }
    }

    private fun gravaTodasRotacoes(rotacao : RotacaoDTO){
        try{
            rotacaoService.salvaRotacao(rotacao)
            excluiRotacaoFirebase(rotacao.id)
        }catch (e:Exception){
            log.info("Erro ao gravar rotacao ${e.message}")
        }
    }

    fun atualizaTodasRotacoes() {
        try {
            val firebaseResult = RotacaoFirebaseResult()
            val apiFuture = FirestoreClient.getFirestore().collection("rotacao").get()
            apiFuture.get().forEach { document ->
                gravaTodasRotacoes(firebaseResult.parse(document))
            }
        }catch (e:Exception){
            log.info("Erro ao gravar todas rotacoes ${e.message}")
        }
    }

    private fun gravaTodasEntregas(entrega : EntregaFirebaseDTO){
        try{
            entregaService.salvaEntrega(entrega)
            //excluiEntregaFirebase(entrega.id.toString())
        }catch (e:Exception){
            log.info("Erro ao gravar entregas ${e.message}")
        }
    }

    fun atualizaTodasEntregas() {
        try {
            val firebaseResult = EntregaFirebaseResult()
            val apiFuture = FirestoreClient.getFirestore().collection("entregas").get()
            apiFuture.get().forEach { document ->
                gravaTodasEntregas(firebaseResult.parse(document))
            }
        }catch (e:Exception){
            log.info("Erro ao gravar todas entregas ${e.message}")
        }
    }

    private fun gravaTodosEventos(evento : EventoDTO){
        try{
            eventoService.salvaEvento(evento)
            excluiEventoFirebase(evento.id.toString())
        }catch (e:Exception){
            log.info("Erro ao gravar eventos ${e.message}")
        }
    }

    fun atualizaTodosEventos() {
        try {
            val firebaseResult = EventoFirebaseResult()
            val apiFuture = FirestoreClient.getFirestore().collection("eventos").get()
            apiFuture.get().forEach { document ->
                gravaTodosEventos(firebaseResult.parse(document))
            }
        }catch (e:Exception){
            log.info("Erro ao gravar todos eventos ${e.message}")
        }
    }
}