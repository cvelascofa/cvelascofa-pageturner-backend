package uoc.tfg.cvelascofa.pageturner_backend.gamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.dto.MonthlyLeaderboardDTO;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.service.interfaces.MonthlyLeaderboardService;

import java.util.List;

@RestController
@RequestMapping("/monthly-leaderboards")
@PreAuthorize("isAuthenticated()")
public class MonthlyLeaderboardController {

    @Autowired
    private MonthlyLeaderboardService monthlyLeaderboardService;

    @GetMapping("/current")
    public ResponseEntity<List<MonthlyLeaderboardDTO>> getCurrentMonthLeaderboard() {
        List<MonthlyLeaderboardDTO> leaderboard = monthlyLeaderboardService.getCurrentMonthLeaderboard();
        return ResponseEntity.ok(leaderboard);
    }

    @GetMapping
    public ResponseEntity<List<MonthlyLeaderboardDTO>> getLeaderboardByMonthAndYear(
            @RequestParam int month,
            @RequestParam int year) {
        List<MonthlyLeaderboardDTO> leaderboard = monthlyLeaderboardService.getLeaderboardByMonthAndYear(month, year);
        return ResponseEntity.ok(leaderboard);
    }

}