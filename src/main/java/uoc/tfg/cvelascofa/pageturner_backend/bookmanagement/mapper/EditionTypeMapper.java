package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.mapper;

import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto.EditionTypeDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.EditionType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EditionTypeMapper {

    EditionTypeDTO toDTO(EditionType entity);
    EditionType toEntity(EditionTypeDTO dto);
    List<EditionTypeDTO> toDTOList(List<EditionType> entities);

}
