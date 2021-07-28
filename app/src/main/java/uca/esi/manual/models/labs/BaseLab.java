package uca.esi.manual.models.labs;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BaseLab implements Parcelable {
    private String userID;               //0
    private LabType labType;             //1
    private String date;                 //2
    private boolean manual = true;       //3
    private boolean inLab = false;       //4
    private float valTeo = 0;            //5
    private float valExp = 0;            //6
    private int errVal = 0;              //7
    private boolean q1 = false;          //8
    private boolean q2 = false;          //9
    private boolean q3 = false;          //10
    private boolean q4 = false;          //11

    protected BaseLab() {
    }

    protected BaseLab(Parcel in) {
        String[] data = in.createStringArray();
        if (data != null) {
            this.userID = data[0];
            this.labType = getTypeFromInt(Integer.parseInt(data[1]));
            this.date = data[2];
            this.manual = Boolean.parseBoolean(data[3]);
            this.inLab = Boolean.parseBoolean(data[4]);
            this.valTeo = Float.parseFloat(data[5]);
            this.valExp = Float.parseFloat(data[6]);
            this.errVal = Integer.parseInt(data[7]);
            this.q1 = Boolean.parseBoolean(data[8]);
            this.q2 = Boolean.parseBoolean(data[9]);
            this.q3 = Boolean.parseBoolean(data[10]);
            this.q4 = Boolean.parseBoolean(data[11]);
        }

    }

    protected BaseLab(String userId, LabType type) {
        this.userID = userId;
        this.labType = type;
        this.date = new Date().toString();
        this.manual = true;
        this.inLab = true;
        this.valTeo = 0f;
        this.valExp = 0f;
        this.errVal = 0;
        this.q1 = false;
        this.q2 = false;
        this.q3 = false;
        this.q4 = false;
    }

    public static int getIntFromType(LabType type) {
        switch (type) {
            case TORSION:
                return 1;
            case PANDEO:
                return 2;
            case FLEXION:
                return 3;
            case TRACCION:
                return 4;
            default:
                return -1;
        }
    }

    public static LabType getTypeFromInt(int tipoInt) {
        switch (tipoInt) {
            case 1:
                return LabType.TORSION;
            case 2:
                return LabType.PANDEO;
            case 3:
                return LabType.FLEXION;
            case 4:
                return LabType.TRACCION;
            default:
                return LabType.INVALID;
        }
    }

    public List<String> toStringList() {
        List<String> data = new ArrayList<>();

        data.add(userID);
        data.add(String.valueOf(getIntFromType(labType)));
        data.add(date);
        data.add(String.valueOf(manual));
        data.add(String.valueOf(inLab));
        data.add(String.valueOf(valTeo));
        data.add(String.valueOf(valExp));
        data.add(String.valueOf(errVal));
        data.add(String.valueOf(q1));
        data.add(String.valueOf(q2));
        data.add(String.valueOf(q3));
        data.add(String.valueOf(q4));

        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(this.toStringList().toArray(new String[0]));
    }

    public String getUserID() {
        return userID;
    }

    public LabType getLabType() {
        return labType;
    }

    public void setLabType(LabType type) {
        this.labType = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public boolean isInLab() {
        return inLab;
    }

    public void setInLab(boolean inLab) {
        this.inLab = inLab;
    }

    public float getValTeo() {
        return valTeo;
    }

    protected void setValTeo(float valTeo) {
        this.valTeo = valTeo;
    }

    public float getValExp() {
        return valExp;
    }

    public void setValExp(float valExp) {
        this.valExp = valExp;
    }

    public int getErrVal() {
        return errVal;
    }

    public void setErrVal(int errVal) {
        this.errVal = errVal;
    }

    public boolean isQ1() {
        return q1;
    }

    public void setQ1(boolean q1) {
        this.q1 = q1;
    }

    public boolean isQ2() {
        return q2;
    }

    public void setQ2(boolean q2) {
        this.q2 = q2;
    }

    public boolean isQ3() {
        return q3;
    }

    public void setQ3(boolean q3) {
        this.q3 = q3;
    }

    public boolean isQ4() {
        return q4;
    }

    public void setQ4(boolean q4) {
        this.q4 = q4;
    }

    protected abstract void setData();

    public abstract String getData();

    @Override
    public @NotNull String toString() {
        return "BaseLab{" +
                "userID='" + userID + '\'' +
                ", labType=" + labType +
                ", date='" + date + '\'' +
                ", manual=" + manual +
                ", inLab=" + inLab +
                ", valTeo=" + valTeo +
                ", valExp=" + valExp +
                ", errVal=" + errVal +
                ", q1=" + q1 +
                ", q2=" + q2 +
                ", q3=" + q3 +
                ", q4=" + q4 +
                '}';
    }

    public enum LabType {
        TORSION,
        PANDEO,
        FLEXION,
        TRACCION,
        INVALID;

        @Override
        public String toString() {
            switch (this) {
                case TORSION:
                    return "Torsion";
                case FLEXION:
                    return "Flexion";
                case TRACCION:
                    return "Traccion";
                case PANDEO:
                    return "Pandeo";
                default:
                    return "";
            }
        }
    }

}


