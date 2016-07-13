package android.internest.com.feedme;

/**
 * Created by stephnoutsa on 7/12/16.
 */
public class Menu {

    // Private variables
    int _mid;
    String day, mName;

    // Empty constructor
    public Menu() {

    }

    // Constructor
    public Menu(int _mid, String day, String mName) {
        this._mid = _mid;
        this.day = day;
        this.mName = mName;
    }

    // Constructor
    public Menu(String day, String mName) {
        this.day = day;
        this.mName = mName;
    }

    // Getter and setter methods
    public int get_mid() {
        return _mid;
    }

    public void set_mid(int _mid) {
        this._mid = _mid;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
