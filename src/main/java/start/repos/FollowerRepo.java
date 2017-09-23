package start.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import start.entities.Follower;

import javax.transaction.Transactional;
import java.util.List;

public interface FollowerRepo extends JpaRepository<Follower, Integer> {

    @Query(value = "select f.followerID from follower f where f.userID=?1")
    public List<Integer> getFollowerIDsByUserID(int userID);

    @Query(value = "select f.userID from follower f where f.followerID=?1")
    public List<Integer> getFollowedIDsByUserID(int userID);

    @Transactional
    @Modifying
    @Query(value = "delete from follower f where f.userID=?1 and f.followerID=?2")
    public void unfollowUser(int userID, int followerID);

}
