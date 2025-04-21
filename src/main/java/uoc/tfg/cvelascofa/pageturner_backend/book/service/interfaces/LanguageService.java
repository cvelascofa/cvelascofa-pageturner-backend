package uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.book.dto.LanguageDTO;

import java.util.List;
import java.util.Optional;

public interface LanguageService {

    LanguageDTO create(LanguageDTO languageDTO);
    List<LanguageDTO> getAll();
    Optional<LanguageDTO> getById(Long id);
    Optional<LanguageDTO> update(Long id, LanguageDTO languageDTO);
    void delete(Long id);

}
