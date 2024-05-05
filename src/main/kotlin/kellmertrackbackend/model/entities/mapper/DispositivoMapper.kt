package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.constants.DispositivoModelo
import kellmertrackbackend.model.dto.DispositivoDTO
import kellmertrackbackend.model.dto.DispositivoFormDTO
import kellmertrackbackend.model.dto.EmpresaDTO
import kellmertrackbackend.model.entities.DispositivoEntity
import kellmertrackbackend.model.entities.EmpresaEntity
import kellmertrackbackend.repository.EmpresaRepository
import kellmertrackbackend.repository.MotoristaRepository
import kellmertrackbackend.repository.VeiculoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class DispositivoMapper(
    private val motoristaRepository: MotoristaRepository,
    private val veiculoRepository: VeiculoRepository,
    private val empresaRepository: EmpresaRepository,
) {

    fun toDispositivoEntity(dispositivo : DispositivoFormDTO) : DispositivoEntity{

        return DispositivoEntity(
            id = dispositivo.id,
            numeroInterno = dispositivo.numeroInterno,
            motorista = motoristaRepository.findByIdOrNull(dispositivo.motoristaId)!!,
            veiculo = veiculoRepository.findByIdentificacao(dispositivo.veiculo)!!,
            modelo = DispositivoModelo.BLAZONLABS,//dispositivo.modelo,
            mac = dispositivo.mac,
            dataVinculo = dispositivo.dataVinculo,
            empresa = empresaRepository.findByCodigo(dispositivo.empresa)
        )
    }

    fun toDispositivoFormDTO(dispositivo : DispositivoEntity?) : DispositivoFormDTO?{
        return dispositivo?.let {
            DispositivoFormDTO(
                id = it.id,
                numeroInterno = it.numeroInterno,
                motoristaId = it.motorista.id,
                //motoristaNome = it.motorista.nome,
                modelo = it.modelo.ordinal,
                veiculo = it.veiculo.identificacao,
                mac = it.mac,
                dataVinculo = it.dataVinculo,
                empresa = it.empresa?.codigo
            )
        }
    }

    fun toDispositivoDTO(dispositivo : DispositivoEntity?) : DispositivoDTO?{
        return dispositivo?.let {
            DispositivoDTO(
                id = it.id,
                numeroInterno = it.numeroInterno,
                mac = it.mac,
                modelo = it.modelo,
                veiculo = it.veiculo.identificacao,
                dataVinculo = it.dataVinculo,
                motoristaId = it.motorista.id,
                motoristaNome = it.motorista.nome,
                empresa = criaEmpresaDTO(it.empresa!!)
            )
        }
    }

    fun criaEmpresaDTO(empresa : EmpresaEntity): EmpresaDTO{
        return EmpresaDTO(
            codigo = empresa.codigo,
            nome = empresa.nome,
            latitude = empresa.latitude,
            longitude = empresa.longitude,
            raio = empresa.raio
        )
    }
}