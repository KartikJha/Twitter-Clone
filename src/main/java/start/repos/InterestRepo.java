package start.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import start.entities.Interest;

import java.util.List;

public interface InterestRepo extends JpaRepository<Interest, Integer> {
    @Query(value = "select i from interest i where i.userID=?1")
    Interest getInterestByUserID(int userID);

    @Query(value = "select i from interest i where i.userID!=?1")
    List<Interest> getInterestsNotHavingUserID(int userID);

    @Query(value = "select i from interest i where i.userID not in ?1")
    List<Interest> getInterestsNotHavingUserIDs(List<Integer> followerIDs);
}
