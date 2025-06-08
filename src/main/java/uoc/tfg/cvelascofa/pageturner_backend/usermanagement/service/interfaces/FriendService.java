package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.service.interfaces;

import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto.FriendDTO;

import java.util.List;

public interface FriendService {

    void sendFriendRequest(FriendDTO friendDTO);
    void acceptFriendRequest(Long senderId, Long recipientId);
    void rejectFriendRequest(Long senderId, Long recipientId);
    List<FriendDTO> getPendingRequests(Long userId);
    List<FriendDTO> getAcceptedFriends(Long userId);
    List<FriendDTO> getReceivedRequests(Long userId);
    List<FriendDTO> getSentRequests(Long userId);
    List<FriendDTO> getAllRelations(Long userId);

}