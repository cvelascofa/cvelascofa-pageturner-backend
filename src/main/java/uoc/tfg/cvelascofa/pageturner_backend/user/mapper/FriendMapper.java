package uoc.tfg.cvelascofa.pageturner_backend.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uoc.tfg.cvelascofa.pageturner_backend.user.dto.FriendDTO;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.Friend;
import uoc.tfg.cvelascofa.pageturner_backend.user.enums.FriendStatus;

@Mapper(componentModel = "spring")
public interface FriendMapper {

    @Mapping(source = "id.senderId", target = "senderId")
    @Mapping(source = "sender.email", target = "senderEmail")
    @Mapping(source = "sender.username", target = "senderUsername")
    @Mapping(source = "id.recipientId", target = "recipientId")
    @Mapping(source = "recipient.email", target = "recipientEmail")
    @Mapping(source = "recipient.username", target = "recipientUsername")
    @Mapping(source = "friendStatus", target = "friendStatus")
    FriendDTO toDTO(Friend friend);

    @Mapping(source = "senderId", target = "id.senderId")
    @Mapping(source = "recipientId", target = "id.recipientId")
    @Mapping(target = "friendStatus", expression = "java(toFriendStatus(friendDTO.getFriendStatus()))")
    Friend toEntity(FriendDTO friendDTO);

    default FriendStatus toFriendStatus(String status) {
        if (status == null) return null;
        try {
            return FriendStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid friend status: " + status);
        }
    }

}