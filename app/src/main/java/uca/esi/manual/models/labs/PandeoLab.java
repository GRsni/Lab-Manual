package uca.esi.manual.models.labs;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uca.esi.manual.models.Constants;

/**
 * The type Pandeo lab.
 */
public class PandeoLab extends BaseLab {

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<BaseLab> CREATOR = new Parcelable.Creator<BaseLab>() {

        @Override
        public BaseLab createFromParcel(Parcel source) {
            return new PandeoLab(source);  //using parcelable constructor
        }

        @Override
        public BaseLab[] newArray(int size) {
            return new BaseLab[size];
        }
    };

    /**
     * The constant FIXTURES_LIST.
     */
    private static final String[] FIXTURES_LIST = {"AA", "AE", "EE"};
    private static final float[] BARS = {Constants.BAR_500_VALUE, Constants.BAR_1000_VALUE};
    private static final float[] LOADS = {883.7f, 1803f, 3535f, 220.9f, 450.8f, 883.7f};
    private float bar = 0;
    private int errBar = 0;
    private String fixtures = "";
    private int errFixtures = 0;

    /**
     * Instantiates a new Pandeo lab.
     *
     * @param userId the user id
     */
    public PandeoLab(String userId) {
        super(userId, LabType.PANDEO);
        setData();
    }

    /**
     * Instantiates a new Pandeo lab.
     *
     * @param in the in
     */
    public PandeoLab(Parcel in) {
        super(in);
        String[] data = in.createStringArray();
        if (data != null) {
            this.bar = Float.parseFloat(data[0]);
            this.errBar = Integer.parseInt(data[1]);
            this.fixtures = data[2];
            this.errFixtures = Integer.parseInt(data[3]);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeStringArray(pandeoToStringList().toArray(new String[0]));
    }

    private List<String> pandeoToStringList() {
        List<String> data = new ArrayList<>();
        data.add(String.valueOf(bar));
        data.add(String.valueOf(errBar));
        data.add(fixtures);
        data.add(String.valueOf(errFixtures));
        return data;
    }

    @Override
    protected void setData() {
        int randCargaTeo = (new Random().nextInt(LOADS.length));
        int randSoporteIndex = (new Random().nextInt(FIXTURES_LIST.length));
        int randBarrasIndex = (new Random().nextInt(BARS.length));
        this.bar = BARS[randBarrasIndex];
        this.fixtures = FIXTURES_LIST[randSoporteIndex];
        setValTeo(LOADS[randCargaTeo]);
    }

    @Override
    public String getData() {
        return fixtures + ":" + bar;
    }

    /**
     * Gets bar.
     *
     * @return the bar
     */
    public float getBar() {
        return bar;
    }

    /**
     * Sets bar.
     *
     * @param bar the bar
     */
    public void setBar(float bar) {
        this.bar = bar;
    }

    /**
     * Gets err bar.
     *
     * @return the err bar
     */
    public int getErrBar() {
        return errBar;
    }

    /**
     * Sets err bar.
     *
     * @param errBar the err bar
     */
    public void setErrBar(int errBar) {
        this.errBar = errBar;
    }

    /**
     * Gets fixtures.
     *
     * @return the fixtures
     */
    public String getFixtures() {
        return fixtures;
    }

    /**
     * Sets fixtures.
     *
     * @param fixtures the fixtures
     */
    public void setFixtures(String fixtures) {
        this.fixtures = fixtures;
    }

    /**
     * Gets err fixtures.
     *
     * @return the err fixtures
     */
    public int getErrFixtures() {
        return errFixtures;
    }

    /**
     * Sets err fixtures.
     *
     * @param errFixtures the err fixtures
     */
    public void setErrFixtures(int errFixtures) {
        this.errFixtures = errFixtures;
    }

    @Override
    public @NotNull String toString() {
        return super.toString() +
                ", PandeoLab{" +
                "bar=" + bar +
                ", errBar=" + errBar +
                ", fixtures='" + fixtures + '\'' +
                ", errFixtures=" + errFixtures +
                '}';
    }
}
