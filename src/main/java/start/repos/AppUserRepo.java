package start.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import start.entities.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Integer> {

    @Query(value = "select u from appuser u where u.handle=?1")
    public AppUser getUserByUserName(String username);

    @Query(value = "select u.handle from appuser u where u.userID=?1")
    String getUserNameByUserID(int userID);

    @Query(value = "select u.userID from appuser u where u.handle=?1")
    public int getUserIDByUserName(String username);

}
