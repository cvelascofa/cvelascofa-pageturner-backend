package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.UserStatisticsDTO;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces.UserStatisticsService;


@RestController
@RequestMapping("/users/{userId}/statistics")
@RequiredArgsConstructor
public class UserStatisticsController {

    private final UserStatisticsService userStatisticsService;

    @GetMapping
    public ResponseEntity<UserStatisticsDTO> getUserStatistics(@PathVariable Long userId) {
        UserStatisticsDTO stats = userStatisticsService.getUserStatistics(userId);
        return ResponseEntity.ok(stats);
    }

}