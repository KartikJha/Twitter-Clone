package start.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import start.entities.TweetLike;

import javax.transaction.Transactional;
import java.util.List;

public interface TweetLikeRepo extends JpaRepository<TweetLike, Integer>{

    @Transactional
    @Modifying
    @Query(value = "delete from tweet_like t where t.userID=?1 and t.tweetID=?2")
    public void deleteTweetLike(int userID, int tweetID);

    @Query(value = "select t from tweet_like t where t.userID=?1")
    public List<TweetLike> getTweetLikeByUserID(int userID);
}

