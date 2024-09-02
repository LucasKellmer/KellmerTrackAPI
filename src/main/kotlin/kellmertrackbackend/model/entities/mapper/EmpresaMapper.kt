package kellmertrackbackend.model.entities.mapper

import kellmertrackbackend.model.dto.EmpresaDTO
import kellmertrackbackend.model.entities.EmpresaEntity
import org.springframework.stereotype.Component

@Component
class EmpresaMapper {

    fun toEmpresaEntity(empresa : EmpresaDTO) : EmpresaEntity{
        return EmpresaEntity(
            codigo = empresa.codigo,
            nome = empresa.descricao,
            latitude = empresa.latitude,
            longitude = empresa.longitude,
            raio = empresa.raio
        )
    }

    fun toEmpresaDTO(empresa : EmpresaEntity) : EmpresaDTO{
        return EmpresaDTO(
            codigo = empresa.codigo,
            descricao = empresa.nome,
            latitude = empresa.latitude,
            longitude = empresa.longitude,
            raio = empresa.raio
        )
    }
}