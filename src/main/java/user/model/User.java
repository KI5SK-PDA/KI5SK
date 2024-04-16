package user.model;

public class User {
    String uid;
    String upw;
    String uname;

    public User(String uid, String upw, String uname) {
        this.uid = uid;
        this.upw = upw;
        this.uname = uname;
    }

    public String getUid() {
        return uid;
    }

    public String getUpw() {
        return upw;
    }

    public String getUname() {
        return uname;
    }
}
