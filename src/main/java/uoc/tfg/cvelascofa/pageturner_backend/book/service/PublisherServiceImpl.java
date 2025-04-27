package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.PublisherDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Publisher;
import uoc.tfg.cvelascofa.pageturner_backend.book.mapper.PublisherMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.PublisherRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces.PublisherService;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private PublisherMapper publisherMapper;

    @Override
    public PublisherDTO create(PublisherDTO dto) {
        Publisher publisher = publisherMapper.toEntity(dto);
        return publisherMapper.toDTO(publisherRepository.save(publisher));
    }

    @Override
    public List<PublisherDTO> getAll() {
        return publisherMapper.toDTOList(publisherRepository.findAll());
    }

    @Override
    public Optional<PublisherDTO> getById(Long id) {
        return publisherRepository.findById(id)
                .map(publisherMapper::toDTO);
    }

    @Override
    public Optional<PublisherDTO> update(Long id, PublisherDTO dto) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (!publisherOptional.isPresent()) {
            return Optional.empty();
        }
        Publisher publisher = publisherOptional.get();
        publisher.setName(dto.getName());
        publisher.setWebsite(dto.getWebsite());
        publisher.setCountry(dto.getCountry());
        return Optional.of(publisherMapper.toDTO(publisherRepository.save(publisher)));
    }

    @Override
    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }

    @Override
    public Page<PublisherDTO> searchPublishersPageable(String name, Pageable pageable) {
        Page<Publisher> publishersPage = publisherRepository.findByNameContainingIgnoreCase(name, pageable);
        return publishersPage.map(publisherMapper::toDTO);
    }
}