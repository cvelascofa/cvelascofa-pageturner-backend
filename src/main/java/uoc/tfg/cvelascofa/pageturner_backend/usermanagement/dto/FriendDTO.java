package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.dto;

import lombok.Data;

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