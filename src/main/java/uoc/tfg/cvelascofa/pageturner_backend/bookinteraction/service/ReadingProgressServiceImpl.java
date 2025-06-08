package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.Book;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.dto.ReadingProgressDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.entity.ReadingProgress;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.enums.ReadingStatus;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.mapper.ReadingProgressMapper;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.repository.ReadingProgressRepository;
import uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.service.interfaces.ReadingProgressService;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces.UserStatisticsService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReadingProgressServiceImpl implements ReadingProgressService {

    @Autowired
    private ReadingProgressRepository readingProgressRepository;

    @Autowired
    private ReadingProgressMapper readingProgressMapper;

    @Autowired
    private UserStatisticsService userStatisticsService;

    @Override
    public ReadingProgressDTO create(ReadingProgressDTO dto, User user, Book book) {
        ReadingProgress readingProgress = new ReadingProgress();
        readingProgress.setUser(user);
        readingProgress.setBook(book);
        readingProgress.setPagesRead(dto.getPagesRead());
        readingProgress.setProgressDate(dto.getProgressDate());
        readingProgress.setReadingStatus(readingProgressMapper.toReadingStatus(dto.getReadingStatus()));

        ReadingProgress savedProgress = readingProgressRepository.save(readingProgress);
        userStatisticsService.updateStatisticsWithProgress(user, savedProgress);

        if (savedProgress.getReadingStatus().name().equalsIgnoreCase(ReadingStatus.COMPLETED.name())) {
            userStatisticsService.updateCompletedBooks(user);
        }

        return readingProgressMapper.toDTO(savedProgress);
    }

    @Override
    public List<ReadingProgressDTO> getAllByUserAndBook(Long userId, Long bookId) {
        List<ReadingProgress> entities = readingProgressRepository.findAllByUserIdAndBookId(userId, bookId);
        return entities.stream()
                .sorted(Comparator.comparing(ReadingProgress::getProgressDate))
                .map(readingProgressMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReadingProgressDTO> update(Long id, ReadingProgressDTO dto) {
        Optional<ReadingProgress> existingProgress = readingProgressRepository.findById(id);
        if (existingProgress.isPresent()) {
            ReadingProgress readingProgress = existingProgress.get();
            if (dto.getPagesRead() != null) {
                readingProgress.setPagesRead(dto.getPagesRead());
            }
            if (dto.getReadingStatus() != null) {
                readingProgress.setReadingStatus(readingProgressMapper.toReadingStatus(dto.getReadingStatus()));
            }
            ReadingProgress updatedProgress = readingProgressRepository.save(readingProgress);
            return Optional.of(readingProgressMapper.toDTO(updatedProgress));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        readingProgressRepository.deleteById(id);
    }

    @Override
    public Optional<ReadingProgressDTO> getById(Long id) {
        Optional<ReadingProgress> readingProgress = readingProgressRepository.findById(id);
        return readingProgress.map(readingProgressMapper::toDTO);
    }

    public Page<ReadingProgressDTO> getPaginatedByUserAndBook(Long userId, Long bookId, Pageable pageable) {
        return readingProgressRepository.findByUserIdAndBookId(userId, bookId, pageable)
                .map(readingProgressMapper::toDTO);
    }

}