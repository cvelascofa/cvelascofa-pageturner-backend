package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.LanguageDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Language;
import uoc.tfg.cvelascofa.pageturner_backend.book.mapper.LanguageMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.LanguageRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces.LanguageService;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private LanguageMapper languageMapper;

    @Override
    public LanguageDTO create(LanguageDTO dto) {
        Language language = languageMapper.toEntity(dto);
        return languageMapper.toDTO(languageRepository.save(language));
    }

    @Override
    public List<LanguageDTO> getAll() {
        return languageMapper.toDTOList(languageRepository.findAll());
    }

    @Override
    public Optional<LanguageDTO> getById(Long id) {
        return languageRepository.findById(id)
                .map(languageMapper::toDTO);
    }

    @Override
    public Optional<LanguageDTO> update(Long id, LanguageDTO dto) {

        Optional<Language> languageOptional = languageRepository.findById(id);
        if (!languageOptional.isPresent()) {
            return Optional.empty();
        }
        Language language = languageOptional.get();
        language.setName(dto.getName());
        language.setCode(dto.getCode());
        return Optional.of(languageMapper.toDTO(languageRepository.save(language)));
    }

    @Override
    public void delete(Long id) {
        languageRepository.deleteById(id);
    }

    @Override
    public Page<LanguageDTO> searchLanguagesPageable(String name, Pageable pageable) {
        Page<Language> languagesPage = languageRepository.findByNameContainingIgnoreCase(name, pageable);
        return languagesPage.map(languageMapper::toDTO);
    }
}