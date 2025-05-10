package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.ReadingStatusDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.enums.ReadingStatusCode;

import java.util.List;

public interface ReadingStatusService  {

    List<ReadingStatusDTO> getAllReadingStatuses();
    ReadingStatusDTO getReadingStatusByCode(ReadingStatusCode statusCode);

}