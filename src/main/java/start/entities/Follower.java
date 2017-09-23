package start.entities;

import javax.persistence.*;

@Entity(name = "follower")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "user_id", nullable = false)
    int userID;
    @Column(name = "follower_id", nullable = false)
    int followerID;

    public Follower() {
    }

    public Follower(int userID, int followerID) {
        this.userID = userID;
        this.followerID = followerID;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getFollowerID() {
        return followerID;
    }

    public void setFollowerID(int followerID) {
        this.followerID = followerID;
    }
}
