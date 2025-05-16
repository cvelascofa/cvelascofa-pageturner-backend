package uoc.tfg.cvelascofa.pageturner_backend.user.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.User;
import uoc.tfg.cvelascofa.pageturner_backend.user.repository.FriendRepository;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.FriendDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.Friend;
import uoc.tfg.cvelascofa.pageturner_backend.user.enums.FriendStatus;
import uoc.tfg.cvelascofa.pageturner_backend.user.mapper.FriendMapper;
import uoc.tfg.cvelascofa.pageturner_backend.user.repository.UserRepository;
import uoc.tfg.cvelascofa.pageturner_backend.user.service.interfaces.FriendService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void sendFriendRequest(FriendDTO friendDTO) {
        if (friendRepository.existsBySenderIdAndRecipientId(friendDTO.getSenderId(), friendDTO.getRecipientId())) {
            throw new IllegalArgumentException("Friend request already exists.");
        }

        User sender = userRepository.findById(friendDTO.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
        User recipient = userRepository.findById(friendDTO.getRecipientId())
                .orElseThrow(() -> new IllegalArgumentException("Recipient not found"));

        Friend friend = friendMapper.toEntity(friendDTO);

        friend.setSender(sender);
        friend.setRecipient(recipient);

        friend.setFriendStatus(FriendStatus.PENDING);
        friendRepository.save(friend);
    }

    @Transactional
    @Override
    public void acceptFriendRequest(Long senderId, Long recipientId) {
        Friend friend = friendRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .orElseThrow(() -> new IllegalArgumentException("Friend request not found."));

        friend.setFriendStatus(FriendStatus.ACCEPTED);
        friendRepository.save(friend);
    }

    @Transactional
    @Override
    public void rejectFriendRequest(Long senderId, Long recipientId) {
        Friend friend = friendRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .orElseThrow(() -> new IllegalArgumentException("Friend request not found."));

        friendRepository.delete(friend);
    }

    @Override
    public List<FriendDTO> getPendingRequests(Long userId) {
        List<Friend> pendingFriends = friendRepository.findByRecipientIdAndFriendStatus(userId, FriendStatus.PENDING);
        return pendingFriends.stream()
                .map(friendMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendDTO> getAcceptedFriends(Long userId) {
        List<Friend> acceptedFriends = friendRepository.findByUserIdAndFriendStatus(userId, FriendStatus.ACCEPTED);
        return acceptedFriends.stream()
                .map(friendMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendDTO> getReceivedRequests(Long userId) {
        return friendRepository.findByRecipientIdAndFriendStatusWithUsers(userId, FriendStatus.PENDING)
                .stream()
                .map(friendMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendDTO> getSentRequests(Long userId) {
        return friendRepository.findBySenderIdAndFriendStatusWithUsers(userId, FriendStatus.PENDING)
                .stream()
                .map(friendMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendDTO> getAllRelations(Long userId) {
        List<Friend> friends = friendRepository.findBySenderIdOrRecipientId(userId, userId);
        return friends.stream()
                .map(friend -> friendMapper.toDTO(friend))
                .collect(Collectors.toList());
    }

}