package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.mapper.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.ReadingStatus;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.enums.ReadingStatusCode;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.repository.ReadingStatusRepository;

@Component
public class ReadingStatusMapperHelper {

    @Autowired
    private ReadingStatusRepository readingStatusRepository;

    public ReadingStatus fromCode(String code) {
        return readingStatusRepository.findByCode(ReadingStatusCode.valueOf(code))
                .orElseThrow(() -> new IllegalArgumentException("Invalid reading status code: " + code));
    }

    public String toCode(ReadingStatus status) {
        return status != null ? status.getCode().name() : null;
    }
}