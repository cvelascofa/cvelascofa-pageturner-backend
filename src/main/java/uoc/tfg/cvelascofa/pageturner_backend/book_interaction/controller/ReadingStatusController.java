package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.dto.ReadingStatusDTO;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.enums.ReadingStatusCode;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.service.interfaces.ReadingStatusService;

import java.util.List;

@RestController
@RequestMapping("/reading-status")
public class ReadingStatusController {

    @Autowired
    private ReadingStatusService readingStatusService;

    @GetMapping("/")
    public List<ReadingStatusDTO> getAllReadingStatuses() {
        return readingStatusService.getAllReadingStatuses();
    }

    @GetMapping("/code/{code}")
    public ReadingStatusDTO getReadingStatusByCode(@PathVariable("code") ReadingStatusCode code) {
        return readingStatusService.getReadingStatusByCode(code);
    }

}