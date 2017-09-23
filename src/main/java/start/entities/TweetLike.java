package start.entities;

import javax.persistence.*;

@Entity(name = "tweet_like")
public class TweetLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    int likeID;
    @Column(name = "user_id", nullable = false)
    int userID;
    @Column(name = "tweet_id", nullable = false)
    int tweetID;

    public TweetLike() {
    }

    public TweetLike(int userID, int tweetID) {
        this.userID = userID;
        this.tweetID = tweetID;
    }

    public int getLikeID() {
        return likeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTweetID() {
        return tweetID;
    }

    public void setTweetID(int tweetID) {
        this.tweetID = tweetID;
    }
}
