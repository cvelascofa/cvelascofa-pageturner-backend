package uoc.tfg.cvelascofa.pageturner_backend.gamification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.dto.UserChallengeDTO;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.dto.UserChallengeWithBadgeDTO;
import uoc.tfg.cvelascofa.pageturner_backend.gamification.service.interfaces.UserChallengeService;

import java.util.List;

@RestController
@RequestMapping("/user-challenges")
@RequiredArgsConstructor
public class UserChallengeController {

    private final UserChallengeService userChallengeService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserChallengeDTO> create(@RequestBody UserChallengeDTO dto) {
        return ResponseEntity.ok(userChallengeService.createUserChallenge(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserChallengeDTO>> getByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(userChallengeService.getUserChallenges(userId));
    }

    @GetMapping("/badges")
    public ResponseEntity<Page<UserChallengeWithBadgeDTO>> getUserBadges(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sort) {
        Page<UserChallengeWithBadgeDTO> badges = userChallengeService.getUserChallengesWithBadges(userId, page, size, sort);
        return ResponseEntity.ok(badges);
    }

    @GetMapping("/users/{userId}/challenges/{challengeId}/status")
    public ResponseEntity<Boolean> hasUserCompletedChallenge(
            @PathVariable Long userId,
            @PathVariable Long challengeId) {

        boolean completed = userChallengeService.hasUserCompletedChallenge(userId, challengeId);
        return ResponseEntity.ok(completed);
    }

}