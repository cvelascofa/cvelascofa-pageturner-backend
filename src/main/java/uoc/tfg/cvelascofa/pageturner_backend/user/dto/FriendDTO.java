package uoc.tfg.cvelascofa.pageturner_backend.user.dto;

import lombok.Data;
import uoc.tfg.cvelascofa.pageturner_backend.user.enums.FriendStatus;

@Data
public class FriendDTO {

    private Long senderId;
    private Long recipientId;
    private String senderEmail;
    private String senderUsername;
    private String recipientEmail;
    private String recipientUsername;
    private String friendStatus;

}