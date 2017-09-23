package start.entities;

import javax.persistence.*;

@Entity(name = "appuser")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    int userID;
    @Column(name = "first", nullable = false)
    String first;
    @Column(name = "last", nullable = false)
    String last;
    @Column(name = "email", unique = true, nullable = false)
    String email;
    @Column(name = "password", nullable = false)
    String password;
    @Column(name = "handle", unique = true, nullable = false)
    String handle;

    public AppUser() {
    }

    public AppUser(String first, String last, String email, String password, String handle) {
        this.first = first;
        this.last = last;
        this.email = email;
        this.password = password;
        this.handle = handle;

    }

    public AppUser(int userID, String first, String last, String email, String password, String handle) {
        this.userID = userID;
        this.first = first;
        this.last = last;
        this.email = email;
        this.password = password;
        this.handle = handle;

    }

    public AppUser(AppUser appUser) {
        this.handle = appUser.getHandle();
        this.password = appUser.getPassword();
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

}
