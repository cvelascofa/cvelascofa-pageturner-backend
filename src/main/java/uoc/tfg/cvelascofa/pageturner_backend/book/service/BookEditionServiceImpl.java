package uoc.tfg.cvelascofa.pageturner_backend.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book.dto.BookEditionDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.BookEdition;
import uoc.tfg.cvelascofa.pageturner_backend.book.mapper.BookEditionMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book.repository.BookEditionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookEditionServiceImpl implements BookEditionService {

    @Autowired
    private BookEditionRepository bookEditionRepository;

    @Autowired
    private BookEditionMapper bookEditionMapper;

    @Override
    public BookEditionDTO createBookEdition(BookEditionDTO bookEditionDTO) {
        BookEdition bookEdition = bookEditionMapper.toEntity(bookEditionDTO);
        BookEdition saved = bookEditionRepository.save(bookEdition);
        return bookEditionMapper.toDTO(saved);
    }

    @Override
    public List<BookEditionDTO> getAllBookEditions() {
        return bookEditionRepository.findAll()
                .stream()
                .map(bookEditionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookEditionDTO> getBookEditionById(Long id) {
        return bookEditionRepository.findById(id)
                .map(bookEditionMapper::toDTO);
    }

    @Override
    public BookEditionDTO updateBookEdition(Long id, BookEditionDTO bookEditionDTO) {
        if (bookEditionRepository.existsById(id)) {
            BookEdition bookEdition = bookEditionMapper.toEntity(bookEditionDTO);
            bookEdition.setId(id);
            BookEdition updated = bookEditionRepository.save(bookEdition);
            return bookEditionMapper.toDTO(updated);
        }
        return null;
    }

    @Override
    public boolean deleteBookEdition(Long id) {
        if (bookEditionRepository.existsById(id)) {
            bookEditionRepository.deleteById(id);
            return true;
        }
        return false;
    }

}