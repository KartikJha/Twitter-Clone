package start.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import start.entities.Tweet;

import java.util.List;

public interface TweetRepo extends JpaRepository<Tweet, Integer> {
    @Query(value = "select t from tweet t where t.hashTag=?1")
    public List<Tweet> getTweetByTag(String tag);

    @Query(value = "select t from tweet t where t.userID=?1")
    public List<Tweet> getTweetByUserID(int userID);

    @Query(value = "select t.userID from tweet t where t.tweetID=?1")
    public int getUserIDByTweetID(int tweetID);
}
