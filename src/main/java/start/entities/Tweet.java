package start.entities;


import javax.persistence.*;

@Entity(name = "tweet")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id")
    int tweetID;
    @Column(name = "user_id", nullable = false)
    int userID;
    @Column(name = "hash_tag")
    String hashTag;
    @Column(name = "content")
    String content;
    @Column(name = "like_count")
    int likeCount;
    @Column(name = "handle")
    String handle;

    public Tweet() {
    }

    public Tweet(int userID, String hashTag, String content, String handle) {
        this.userID = userID;
        this.hashTag = hashTag;
        this.content = content;
        this.likeCount=0;
        this.handle=handle;
    }

    public Tweet(int userID, String hashTag, String content, String handle, int likeCount) {
        this.userID = userID;
        this.hashTag = hashTag;
        this.content = content;
        this.likeCount=likeCount;
        this.handle=handle;
    }


    public Tweet(int tweetID, int userID, String hashTag, String content, int likeCount, String handle) {
        this.tweetID = tweetID;
        this.userID = userID;
        this.hashTag = hashTag;
        this.content = content;
        this.likeCount = likeCount;
        this.handle=handle;
    }

    public int getTweetID() {
        return tweetID;
    }

    public void setTweetID(int tweetID) {
        this.tweetID = tweetID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetID=" + tweetID +
                ", userID=" + userID +
                ", hashTag='" + hashTag + '\'' +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", handle='" + handle + '\'' +
                '}';
    }
}
