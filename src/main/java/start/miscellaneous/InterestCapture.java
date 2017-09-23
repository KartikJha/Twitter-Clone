package start.miscellaneous;

import java.io.Serializable;

public class InterestCapture implements Serializable{

    boolean cricket;
    boolean football;
    boolean golf;
    boolean mma;
    boolean kabaddi;
    int userID;
    int interestID;

    public InterestCapture() {
    }

    public InterestCapture(int interestID, int userID, boolean cricket, boolean football, boolean golf, boolean mma, boolean kabaddi) {
        this.cricket = cricket;
        this.football = football;
        this.golf = golf;
        this.mma = mma;
        this.kabaddi = kabaddi;
        this.interestID = interestID;
        this.userID = userID;
    }

    public InterestCapture( boolean cricket, boolean football, boolean golf, boolean mma, boolean kabaddi) {
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
