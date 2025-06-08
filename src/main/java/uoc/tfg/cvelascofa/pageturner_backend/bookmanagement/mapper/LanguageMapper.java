package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.mapper;

import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto.LanguageDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.Language;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LanguageMapper {

    LanguageDTO toDTO(Language entity);
    Language toEntity(LanguageDTO dto);
    List<LanguageDTO> toDTOList(List<Language> entities);

}