package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.mapper;

import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto.PublisherDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.Publisher;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    PublisherDTO toDTO(Publisher entity);
    Publisher toEntity(PublisherDTO dto);
    List<PublisherDTO> toDTOList(List<Publisher> entities);

}