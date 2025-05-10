package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.ReadingStatusDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.ReadingStatus;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.enums.ReadingStatusCode;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.mapper.ReadingStatusMapper;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.repository.ReadingStatusRepository;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces.ReadingStatusService;

import java.util.List;
import java.util.Optional;

@Service
public class ReadingStatusServiceImpl implements ReadingStatusService {

    @Autowired
    private ReadingStatusRepository readingStatusRepository;

    @Autowired
    private ReadingStatusMapper readingStatusMapper;

    @Override
    public List<ReadingStatusDTO> getAllReadingStatuses() {
        List<ReadingStatus> statuses = readingStatusRepository.findAll();
        return readingStatusMapper.toDTOList(statuses);
    }

    @Override
    public ReadingStatusDTO getReadingStatusByCode(ReadingStatusCode statusCode) {
        Optional<ReadingStatus> status = readingStatusRepository.findByCode(statusCode);
        return status.map(readingStatusMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("ReadingStatus not found: " + statusCode));
    }

}