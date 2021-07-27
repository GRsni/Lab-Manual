package uca.esi.manual.models.labs;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uca.esi.manual.models.Constants;

public class PandeoLab extends BaseLab {

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

    public static final String[] FIXTURES_LIST = {"AA", "AE", "EE"};
    private static final float[] BARS = {Constants.BAR_500_VALUE, Constants.BAR_1000_VALUE};
    private static final float[] LOADS = {883.7f, 1803f, 3535f, 220.9f, 450.8f, 883.7f};
    private float bar = 0;
    private int errBar = 0;
    private String fixtures = "";
    private int errFixtures = 0;

    public PandeoLab(String userId) {
        super(userId, LabType.PANDEO);
        setData();
    }

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

    public float getBar() {
        return bar;
    }

    public void setBar(float bar) {
        this.bar = bar;
    }

    public int getErrBar() {
        return errBar;
    }

    public void setErrBar(int errBar) {
        this.errBar = errBar;
    }

    public String getFixtures() {
        return fixtures;
    }

    public void setFixtures(String fixtures) {
        this.fixtures = fixtures;
    }

    public int getErrFixtures() {
        return errFixtures;
    }

    public void setErrFixtures(int errFixtures) {
        this.errFixtures = errFixtures;
    }

}
