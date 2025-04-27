package uoc.tfg.cvelascofa.pageturner_backend.book.mapper;

import org.mapstruct.Mapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.PublisherDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Publisher;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    PublisherDTO toDTO(Publisher entity);
    Publisher toEntity(PublisherDTO dto);
    List<PublisherDTO> toDTOList(List<Publisher> entities);

}