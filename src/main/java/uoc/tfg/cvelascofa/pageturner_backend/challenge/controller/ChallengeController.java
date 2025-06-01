package uoc.tfg.cvelascofa.pageturner_backend.challenge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.dto.ChallengeDTO;
import uoc.tfg.cvelascofa.pageturner_backend.challenge.service.interfaces.ChallengeService;

import java.util.List;

@RestController
@RequestMapping("/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<ChallengeDTO> create(@RequestBody ChallengeDTO challengeDTO) {
        return ResponseEntity.ok(challengeService.createChallenge(challengeDTO));
    }

    @GetMapping
    public ResponseEntity<List<ChallengeDTO>> getAll() {
        return ResponseEntity.ok(challengeService.getAllChallenges());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChallengeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(challengeService.getChallengeById(id));
    }

}