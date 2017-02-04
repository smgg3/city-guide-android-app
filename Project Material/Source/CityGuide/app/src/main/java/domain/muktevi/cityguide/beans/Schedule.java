package domain.muktevi.cityguide.beans;

/**
 * Created by Vijay Kumar Tummala on 4/12/2016.
 */
public class Schedule {

    private String oid;
    private String user;
    private String venuename;
    private String date;
    private String time;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getVenueName() {
        return venuename;
    }

    public void setVenueName(String venuename) {
        this.venuename = venuename;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



}
