package uca.esi.manual.models.labs;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Base lab.
 */
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

    /**
     * Instantiates a new Base lab.
     */
    protected BaseLab() {
    }

    /**
     * Instantiates a new Base lab.
     *
     * @param in the in
     */
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

    /**
     * Instantiates a new Base lab.
     *
     * @param userId the user id
     * @param type   the type
     */
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

    /**
     * Gets int from type.
     *
     * @param type the type
     * @return the int from type
     */
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

    /**
     * Gets type from int.
     *
     * @param typeInt the type int
     * @return the type from int
     */
    public static LabType getTypeFromInt(int typeInt) {
        switch (typeInt) {
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

    /**
     * To string list list.
     *
     * @return the list
     */
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

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Gets lab type.
     *
     * @return the lab type
     */
    public LabType getLabType() {
        return labType;
    }

    /**
     * Sets lab type.
     *
     * @param type the type
     */
    public void setLabType(LabType type) {
        this.labType = type;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Is manual boolean.
     *
     * @return the boolean
     */
    public boolean isManual() {
        return manual;
    }

    /**
     * Sets manual.
     *
     * @param manual the manual
     */
    public void setManual(boolean manual) {
        this.manual = manual;
    }

    /**
     * Is in lab boolean.
     *
     * @return the boolean
     */
    public boolean isInLab() {
        return inLab;
    }

    /**
     * Sets in lab.
     *
     * @param inLab the in lab
     */
    public void setInLab(boolean inLab) {
        this.inLab = inLab;
    }

    /**
     * Gets val teo.
     *
     * @return the val teo
     */
    public float getValTeo() {
        return valTeo;
    }

    /**
     * Sets val teo.
     *
     * @param valTeo the val teo
     */
    protected void setValTeo(float valTeo) {
        this.valTeo = valTeo;
    }

    /**
     * Gets val exp.
     *
     * @return the val exp
     */
    public float getValExp() {
        return valExp;
    }

    /**
     * Sets val exp.
     *
     * @param valExp the val exp
     */
    public void setValExp(float valExp) {
        this.valExp = valExp;
    }

    /**
     * Gets err val.
     *
     * @return the err val
     */
    public int getErrVal() {
        return errVal;
    }

    /**
     * Sets err val.
     *
     * @param errVal the err val
     */
    public void setErrVal(int errVal) {
        this.errVal = errVal;
    }

    /**
     * Is q 1 boolean.
     *
     * @return the boolean
     */
    public boolean isQ1() {
        return q1;
    }

    /**
     * Sets q 1.
     *
     * @param q1 the q 1
     */
    public void setQ1(boolean q1) {
        this.q1 = q1;
    }

    /**
     * Is q 2 boolean.
     *
     * @return the boolean
     */
    public boolean isQ2() {
        return q2;
    }

    /**
     * Sets q 2.
     *
     * @param q2 the q 2
     */
    public void setQ2(boolean q2) {
        this.q2 = q2;
    }

    /**
     * Is q 3 boolean.
     *
     * @return the boolean
     */
    public boolean isQ3() {
        return q3;
    }

    /**
     * Sets q 3.
     *
     * @param q3 the q 3
     */
    public void setQ3(boolean q3) {
        this.q3 = q3;
    }

    /**
     * Is q 4 boolean.
     *
     * @return the boolean
     */
    public boolean isQ4() {
        return q4;
    }

    /**
     * Sets q 4.
     *
     * @param q4 the q 4
     */
    public void setQ4(boolean q4) {
        this.q4 = q4;
    }

    /**
     * Sets data.
     */
    protected abstract void setData();

    /**
     * Gets data.
     *
     * @return the data
     */
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

    /**
     * The enum Lab type.
     */
    public enum LabType {
        /**
         * Torsion lab type.
         */
        TORSION,
        /**
         * Pandeo lab type.
         */
        PANDEO,
        /**
         * Flexion lab type.
         */
        FLEXION,
        /**
         * Traccion lab type.
         */
        TRACCION,
        /**
         * Invalid lab type.
         */
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


