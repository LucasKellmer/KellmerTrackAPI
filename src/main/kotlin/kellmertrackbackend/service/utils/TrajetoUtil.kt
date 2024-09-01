package kellmertrackbackend.service.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kellmertrackbackend.model.dto.TrajetoDTO
import kellmertrackbackend.model.entities.TrajetoEntity
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

class TrajetoUtil {

    fun comprimirTrajeto(trajetoDiario: List<TrajetoEntity>): ByteArray {
        ByteArrayOutputStream().use { byteArrayOutputStream ->
            ZipOutputStream(byteArrayOutputStream).use { zipOut ->
                val json = ObjectMapper().writeValueAsString(trajetoDiario)
                zipOut.putNextEntry(ZipEntry("trajetos.json"))
                zipOut.write(json.toByteArray())
                zipOut.closeEntry()
            }
            return byteArrayOutputStream.toByteArray()
        }
    }

    fun descomprimirTrajeto(trajetoZip: ByteArray): List<TrajetoEntity> {
        val trajetoList = mutableListOf<TrajetoEntity>()

        ZipInputStream(ByteArrayInputStream(trajetoZip)).use { zipIn ->
            var entry: ZipEntry? = zipIn.nextEntry
            while (entry != null) {
                if (entry.name == "trajetos.json") {
                    val jsonString = String(zipIn.readAllBytes()) // Lê todos os bytes da entrada
                    trajetoList.addAll(parseJsonToTrajetoList(jsonString))
                }
                zipIn.closeEntry()
                entry = zipIn.nextEntry
            }
        }
        return trajetoList
    }

    private fun parseJsonToTrajetoList(jsonString: String): List<TrajetoEntity> {
        return ObjectMapper().readValue(jsonString, object : TypeReference<List<TrajetoEntity>>() {})
    }
}