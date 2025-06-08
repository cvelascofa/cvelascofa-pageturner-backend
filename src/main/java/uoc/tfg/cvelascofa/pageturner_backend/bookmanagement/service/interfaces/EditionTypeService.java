package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto.EditionTypeDTO;

import java.util.List;
import java.util.Optional;

public interface EditionTypeService {

    EditionTypeDTO create(EditionTypeDTO editionTypeDTO);
    List<EditionTypeDTO> getAll();
    Optional<EditionTypeDTO> getById(Long id);
    Optional<EditionTypeDTO> update(Long id, EditionTypeDTO editionTypeDTO);
    void delete(Long id);
    Page<EditionTypeDTO> searchEditionTypesPageable(String name, Pageable pageable);

}
