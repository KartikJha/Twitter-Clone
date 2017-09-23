package start.miscellaneous;



public class AppUserCapture {


    int userID;

    String first;

    String last;

    String email;

    String password;

    String handle;

    InterestCapture interestCapture;

    public AppUserCapture() {
    }

    public AppUserCapture(int userID, String first, String email, String last, String password, String handle, InterestCapture interestCapture) {
        this.userID = userID;
        this.first = first;
        this.email = email;
        this.last = last;
        this.password = password;
        this.handle = handle;
        this.interestCapture = interestCapture;
    }

    public AppUserCapture(String first, String last, String email, String password, String handle, InterestCapture interestCapture) {
        this.first = first;
        this.last = last;
        this.email = email;
        this.password = password;
        this.handle = handle;
        this.interestCapture = interestCapture;
    }

    public int getUserID() {
        return userID;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public InterestCapture getInterestCapture() {
        return interestCapture;
    }

    public void setInterestCapture(InterestCapture interestCapture) {
        this.interestCapture = interestCapture;
    }
}
