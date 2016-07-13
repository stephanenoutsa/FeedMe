package android.internest.com.feedme;

/**
 * Created by stephnoutsa on 7/12/16.
 */
public class Resto {

    // Private variables
    int _rid;
    String rname, rlogo, rlocation, day;

    // Empty constructor
    public Resto() {

    }

    // Constructor
    public Resto(int _rid, String rname, String rlogo, String rlocation, String day) {
        this._rid = _rid;
        this.rname = rname;
        this.rlogo = rlogo;
        this.rlocation = rlocation;
        this.day = day;
    }

    // Constructor
    public Resto(String rname, String rlogo, String rlocation, String day) {
        this.rname = rname;
        this.rlogo = rlogo;
        this.rlocation = rlocation;
        this.day = day;
    }

    // Getter and setter methods
    public int get_rid() {
        return _rid;
    }

    public void set_rid(int _rid) {
        this._rid = _rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRlogo() {
        return rlogo;
    }

    public void setRlogo(String rlogo) {
        this.rlogo = rlogo;
    }

    public String getRlocation() {
        return rlocation;
    }

    public void setRlocation(String rlocation) {
        this.rlocation = rlocation;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
