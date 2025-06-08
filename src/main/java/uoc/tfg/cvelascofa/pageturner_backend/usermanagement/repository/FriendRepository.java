package uoc.tfg.cvelascofa.pageturner_backend.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.Friend;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.entity.FriendId;
import uoc.tfg.cvelascofa.pageturner_backend.usermanagement.enums.FriendStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, FriendId> {

    List<Friend> findByRecipientIdAndFriendStatus(Long recipientId, FriendStatus friendStatus);
    boolean existsBySenderIdAndRecipientId(Long senderId, Long recipientId);
    Optional<Friend> findBySenderIdAndRecipientId(Long senderId, Long recipientId);
    List<Friend> findBySenderIdOrRecipientId(Long senderId, Long recipientId);

    @Query("SELECT f FROM Friend f WHERE (f.id.senderId = :userId OR f.id.recipientId = :userId) AND f.friendStatus = :status")
    List<Friend> findByUserIdAndFriendStatus(@Param("userId") Long userId, @Param("status") FriendStatus status);


    @Query("SELECT f FROM Friend f JOIN FETCH f.sender JOIN FETCH f.recipient WHERE f.recipient.id = :userId AND f.friendStatus = :status")
    List<Friend> findByRecipientIdAndFriendStatusWithUsers(@Param("userId") Long userId, @Param("status") FriendStatus status);

    @Query("SELECT f FROM Friend f JOIN FETCH f.sender JOIN FETCH f.recipient WHERE f.sender.id = :userId AND f.friendStatus = :status")
    List<Friend> findBySenderIdAndFriendStatusWithUsers(@Param("userId") Long userId, @Param("status") FriendStatus status);

}