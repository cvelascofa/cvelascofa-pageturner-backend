package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.AuthorDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Author;
import uoc.tfg.cvelascofa.pageturner_backend.book.mapper.AuthorMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.AuthorRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book.service.interfaces.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public AuthorDTO create(AuthorDTO dto) {
        Author author = authorMapper.toEntity(dto);
        return authorMapper.toDTO(authorRepository.save(author));
    }

    @Override
    public List<AuthorDTO> getAll() {
        return authorMapper.toDTOList(authorRepository.findAll());
    }

    @Override
    public Optional<AuthorDTO> getById(Long id) {
        return authorRepository.findById(id)
                .map(authorMapper::toDTO);
    }

    @Override
    public Optional<AuthorDTO> update(Long id, AuthorDTO dto) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (!optionalAuthor.isPresent()) {
            return Optional.empty();
        }

        Author author = optionalAuthor.get();
        author.setName(dto.getName());
        author.setBio(dto.getBio());
        author.setWebsite(dto.getWebsite());
        author.setFollowersCount(dto.getFollowersCount());

        return Optional.of(authorMapper.toDTO(authorRepository.save(author)));
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Page<AuthorDTO> searchAuthorsPageable(String name, Pageable pageable) {
        Page<Author> authorsPage = authorRepository.findByNameContainingIgnoreCase(name, pageable);
        return authorsPage.map(authorMapper::toDTO);
    }
}
