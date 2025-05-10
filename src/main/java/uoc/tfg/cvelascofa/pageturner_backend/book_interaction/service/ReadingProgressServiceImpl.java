package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.ReadingProgressDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.ReadingProgress;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.mapper.ReadingProgressMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.repository.ReadingProgressRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces.ReadingProgressService;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;

import java.util.Optional;

@Service
public class ReadingProgressServiceImpl implements ReadingProgressService {

    @Autowired
    private ReadingProgressRepository readingProgressRepository;

    @Autowired
    private ReadingProgressMapper readingProgressMapper;

    @Override
    public ReadingProgressDTO create(ReadingProgressDTO dto, User user, Book book) {
        ReadingProgress readingProgress = readingProgressMapper.toEntity(dto);
        readingProgress.setUser(user);
        readingProgress.setBook(book);

        ReadingProgress savedProgress = readingProgressRepository.save(readingProgress);
        return readingProgressMapper.toDTO(savedProgress);
    }

    @Override
    public Optional<ReadingProgressDTO> getByUserAndBook(Long userId, Long bookId) {
        Optional<ReadingProgress> readingProgress = readingProgressRepository.findByUserIdAndBookId(userId, bookId);
        return readingProgress.map(readingProgressMapper::toDTO);
    }

    @Override
    public Optional<ReadingProgressDTO> update(Long userId, Long bookId, ReadingProgressDTO dto) {
        Optional<ReadingProgress> existingProgress = readingProgressRepository.findByUserIdAndBookId(userId, bookId);
        if (existingProgress.isPresent()) {
            ReadingProgress readingProgress = existingProgress.get();
            if (dto.getPagesRead() != null) {
                readingProgress.setPagesRead(dto.getPagesRead());
            }
            ReadingProgress updatedProgress = readingProgressRepository.save(readingProgress);
            return Optional.of(readingProgressMapper.toDTO(updatedProgress));
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long userId, Long bookId) {
        readingProgressRepository.deleteByUserIdAndBookId(userId, bookId);
    }
}

