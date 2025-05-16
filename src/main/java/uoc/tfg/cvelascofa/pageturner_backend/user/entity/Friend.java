package uoc.tfg.cvelascofa.pageturner_backend.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.shared.BaseEntity;
import uoc.tfg.cvelascofa.pageturner_backend.user.enums.FriendStatus;

@Data
@Entity
@Table(name = "friends")
public class Friend {

    @EmbeddedId
    private FriendId id;

    @ManyToOne(optional = false)
    @MapsId("senderId")
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(optional = false)
    @MapsId("recipientId")
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @Enumerated(EnumType.STRING)
    @Column(name = "friend_status", nullable = false)
    private FriendStatus friendStatus;

}