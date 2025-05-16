package uoc.tfg.cvelascofa.pageturner_backend.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uoc.tfg.cvelascofa.pageturner_backend.security.LibraryUserDetails;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.FriendDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces.FriendService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/request")
    public ResponseEntity<Void> sendFriendRequest(@RequestBody FriendDTO friendDTO) {
        try {
            Long userId = getAuthenticatedUserId();
            if (!userId.equals(friendDTO.getSenderId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            friendService.sendFriendRequest(friendDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/accept/{senderId}/{recipientId}")
    public ResponseEntity<Map<String, String>> acceptFriendRequest(@PathVariable Long senderId, @PathVariable Long recipientId) {
        try {
            Long userId = getAuthenticatedUserId();
            if (!userId.equals(recipientId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "Unauthorized"));
            }
            friendService.acceptFriendRequest(senderId, recipientId);
            return ResponseEntity.ok(Map.of("message", "Request accepted"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Accept error"));
        }
    }

    @DeleteMapping("/reject/{senderId}/{recipientId}")
    public ResponseEntity<Void> rejectFriendRequest(@PathVariable Long senderId, @PathVariable Long recipientId) {
        try {
            Long userId = getAuthenticatedUserId();
            if (!userId.equals(senderId) && !userId.equals(recipientId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            friendService.rejectFriendRequest(senderId, recipientId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/requests/pending")
    public ResponseEntity<List<FriendDTO>> getPendingFriendRequests() {
        try {
            Long userId = getAuthenticatedUserId();
            List<FriendDTO> pendingRequests = friendService.getPendingRequests(userId);
            return ResponseEntity.ok(pendingRequests);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }

    @GetMapping
    public ResponseEntity<List<FriendDTO>> getAcceptedFriends() {
        try {
            Long userId = getAuthenticatedUserId();
            List<FriendDTO> acceptedFriends = friendService.getAcceptedFriends(userId);
            return ResponseEntity.ok(acceptedFriends);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }

    private Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof LibraryUserDetails) {
            LibraryUserDetails userDetails = (LibraryUserDetails) authentication.getPrincipal();
            return userDetails.getId();
        }
        throw new RuntimeException("User not authenticated");
    }

    @GetMapping("/requests/received")
    public ResponseEntity<List<FriendDTO>> getReceivedFriendRequests() {
        try {
            Long userId = getAuthenticatedUserId();
            List<FriendDTO> receivedRequests = friendService.getReceivedRequests(userId);
            return ResponseEntity.ok(receivedRequests);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }

    @GetMapping("/requests/sent")
    public ResponseEntity<List<FriendDTO>> getSentFriendRequests() {
        try {
            Long userId = getAuthenticatedUserId();
            List<FriendDTO> sentRequests = friendService.getSentRequests(userId);
            return ResponseEntity.ok(sentRequests);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<FriendDTO>> getAllFriendsAndRequests(@PathVariable Long userId) {
        try {
            Long authUserId = getAuthenticatedUserId();
            if (!authUserId.equals(userId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.emptyList());
            }
            List<FriendDTO> allRelations = friendService.getAllRelations(userId);
            return ResponseEntity.ok(allRelations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }


}