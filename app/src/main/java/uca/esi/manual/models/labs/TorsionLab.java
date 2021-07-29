package uca.esi.manual.models.labs;


import android.os.Parcel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TorsionLab extends BaseLab {

    public static final Creator<BaseLab> CREATOR = new Creator<BaseLab>() {

        @Override
        public BaseLab createFromParcel(Parcel source) {
            return new TorsionLab(source);  //using parcelable constructor
        }

        @Override
        public BaseLab[] newArray(int size) {
            return new BaseLab[size];
        }
    };


    private static final int[] LOADS_TORSION = {5, 10, 15, 20};
    private static final float[] LOAD_MOMENT = {.5f, 1.0f, 1.5f, 2.0f};
    private static final float[] AMPLI_VALUES = {-.035f, -.073f, -.112f, -.15f};
    private int weights = 0;
    private int errWeights = 0;
    private float ampliTeo = 0;
    private float ampliExp = 0;

    public TorsionLab(String userId) {
        super(userId, LabType.TORSION);
        setData();
    }

    public TorsionLab(Parcel in) {
        super(in);
        String[] data = in.createStringArray();
        if (data != null) {
            this.weights = Integer.parseInt(data[0]);
            this.errWeights = Integer.parseInt(data[1]);
            this.ampliTeo = Float.parseFloat(data[2]);
            this.ampliExp = Float.parseFloat(data[3]);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeStringArray(torsionToStringList().toArray(new String[0]));
    }

    private List<String> torsionToStringList() {
        List<String> data = new ArrayList<>();
        data.add(String.valueOf(weights));
        data.add(String.valueOf(errWeights));
        data.add(String.valueOf(ampliTeo));
        data.add(String.valueOf(ampliExp));

        return data;
    }

    @Override
    protected void setData() {
        int index = (new Random().nextInt(LOADS_TORSION.length));
        this.weights = LOADS_TORSION[index];
        setValTeo(LOAD_MOMENT[index]);
        this.ampliTeo = AMPLI_VALUES[index];
    }

    @Override
    public String getData() {
        return String.valueOf(weights);
    }

    public int getWeights() {
        return weights;
    }

    public void setWeights(int weights) {
        this.weights = weights;
    }

    public int getErrWeights() {
        return errWeights;
    }

    public void setErrWeights(int errWeights) {
        this.errWeights = errWeights;
    }

    public float getAmpliTeo() {
        return ampliTeo;
    }

    public float getAmpliExp() {
        return ampliExp;
    }

    public void setAmpliExp(float ampliExp) {
        this.ampliExp = ampliExp;
    }

    @Override
    public @NotNull String toString() {
        return super.toString() + ", " +
                "TorsionLab{" +
                "weights='" + weights + '\'' +
                ", errWeights=" + errWeights +
                ", ampliTeo=" + ampliTeo +
                ", ampliExp=" + ampliExp +
                '}';
    }
}
