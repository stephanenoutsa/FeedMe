package android.internest.com.feedme;

/**
 * Created by stephnoutsa on 7/12/16.
 */
public class Food {

    // Private variables
    int _fid;
    String fname, ficon, mName;

    // Empty constructor
    public Food() {

    }

    // Constructor
    public Food(int _fid, String fname, String ficon, String mName) {
        this._fid = _fid;
        this.fname = fname;
        this.ficon = ficon;
        this.mName = mName;
    }

    // Constructor
    public Food(String fname, String ficon, String mName) {
        this.fname = fname;
        this.ficon = ficon;
        this.mName = mName;
    }

    // Getter and setter methods
    public int get_fid() {
        return _fid;
    }

    public void set_fid(int _fid) {
        this._fid = _fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFicon() {
        return ficon;
    }

    public void setFicon(String ficon) {
        this.ficon = ficon;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
