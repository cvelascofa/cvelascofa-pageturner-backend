package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto.EditionTypeDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.EditionType;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.mapper.EditionTypeMapper;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.repository.EditionTypeRepository;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.service.interfaces.EditionTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class EditionTypeServiceImpl implements EditionTypeService {
    @Autowired
    private EditionTypeRepository editionTypeRepository;

    @Autowired
    private EditionTypeMapper editionTypeMapper;

    @Override
    public EditionTypeDTO create(EditionTypeDTO dto) {
        EditionType editionType = editionTypeMapper.toEntity(dto);
        return editionTypeMapper.toDTO(editionTypeRepository.save(editionType));
    }

    @Override
    public List<EditionTypeDTO> getAll() {
        return editionTypeMapper.toDTOList(editionTypeRepository.findAll());
    }

    @Override
    public Optional<EditionTypeDTO> getById(Long id) {
        return editionTypeRepository.findById(id)
                .map(editionTypeMapper::toDTO);
    }

    @Override
    public Optional<EditionTypeDTO> update(Long id, EditionTypeDTO dto) {

        Optional<EditionType> editionTypeOptional = editionTypeRepository.findById(id);
        if (!editionTypeOptional.isPresent()) {
            return Optional.empty();
        }
        EditionType editionType = editionTypeOptional.get();
        editionType.setName(dto.getName());
        return Optional.of(editionTypeMapper.toDTO(editionTypeRepository.save(editionType)));
    }

    @Override
    public void delete(Long id) {
        editionTypeRepository.deleteById(id);
    }

    @Override
    public Page<EditionTypeDTO> searchEditionTypesPageable(String name, Pageable pageable) {
        Page<EditionType> editionTypesPage = editionTypeRepository.findByNameContainingIgnoreCase(name, pageable);
        return editionTypesPage.map(editionTypeMapper::toDTO);
    }
}