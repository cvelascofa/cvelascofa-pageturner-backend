package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto.PublisherDTO;

import java.util.List;
import java.util.Optional;

public interface PublisherService {

    PublisherDTO create(PublisherDTO publisherDTO);
    List<PublisherDTO> getAll();
    Optional<PublisherDTO> getById(Long id);
    Optional<PublisherDTO> update(Long id, PublisherDTO publisherDTO);
    void delete(Long id);
    Page<PublisherDTO> searchPublishersPageable(String name, Pageable pageable);

}