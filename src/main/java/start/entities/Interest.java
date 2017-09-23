package start.entities;

import javax.persistence.*;

@Entity(name = "interest")
public class Interest {

    @Id
    @Column(name = "interest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int interestID;
    @Column(name = "cricket", nullable = false)
    boolean cricket;
    @Column(name = "football", nullable = false)
    boolean football;
    @Column(name = "golf", nullable = false)
    boolean golf;
    @Column(name = "mma", nullable = false)
    boolean mma;
    @Column(name = "kabaddi", nullable = false)
    boolean kabaddi;
    @Column(name = "user_id")
    int userID;

    public Interest() {
    }

    public Interest(int interestID, int userID,boolean cricket, boolean football, boolean golf, boolean mma, boolean kabaddi) {
        this.interestID = interestID;
        this.userID = userID;
        this.cricket = cricket;
        this.football = football;
        this.golf = golf;
        this.mma = mma;
        this.kabaddi = kabaddi;
    }

    public Interest(int userID, boolean cricket, boolean football, boolean golf, boolean mma, boolean kabaddi) {
        this.userID = userID;
        this.cricket = cricket;
        this.football = football;
        this.golf = golf;
        this.mma = mma;
        this.kabaddi = kabaddi;
    }

    public int getInterestID() {
        return interestID;
    }

    public void setInterestID(int interestID) {
        this.interestID = interestID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isCricket() {
        return cricket;
    }

    public void setCricket(boolean cricket) {
        this.cricket = cricket;
    }

    public boolean isFootball() {
        return football;
    }

    public void setFootball(boolean football) {
        this.football = football;
    }

    public boolean isGolf() {
        return golf;
    }

    public void setGolf(boolean golf) {
        this.golf = golf;
    }

    public boolean isMma() {
        return mma;
    }

    public void setMma(boolean mma) {
        this.mma = mma;
    }

    public boolean isKabaddi() {
        return kabaddi;
    }

    public void setKabaddi(boolean kabaddi) {
        this.kabaddi = kabaddi;
    }
}
