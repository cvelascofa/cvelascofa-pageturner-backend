package uoc.tfg.cvelascofa.pageturner_backend.entity.friend;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.entity.BaseEntity;
import uoc.tfg.cvelascofa.pageturner_backend.entity.user.User;

@Data
@Entity
@Table(name = "friends")
@EqualsAndHashCode(callSuper = true)
public class Friend extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private User friend;

    @ManyToOne
    @JoinColumn(name = "friend_status_id", nullable = false)
    private FriendStatus friendStatus;

}