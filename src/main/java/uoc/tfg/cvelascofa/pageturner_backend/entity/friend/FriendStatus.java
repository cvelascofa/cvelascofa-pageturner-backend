package uoc.tfg.cvelascofa.pageturner_backend.entity.friend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uoc.tfg.cvelascofa.pageturner_backend.entity.BaseEntity;

@Data
@Entity
@Table(name = "friend_statuses")
@EqualsAndHashCode(callSuper = true)
public class FriendStatus extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

}