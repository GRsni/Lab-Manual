package uca.esi.manual.models.labs

import android.os.Parcelable
import java.util.*

abstract class BaseLab(
    private var pId: String?, type: LabType?
) : Parcelable {

    enum class LabType {
        TORSION, PANDEO, FLEXION, TRACCION, INVALID;

        override fun toString(): String {
            return when (this) {
                TORSION -> "torsion"
                FLEXION -> "flexion"
                TRACCION -> "traccion"
                PANDEO -> "pandeo"
                else -> ""
            }
        }
    }

    private var typeP //1
            : LabType? = type
    private var date //2
            : String? = null
    private var manual //3
            = false
    private var inLab //4
            = false
    private var valTeo //5
            = 0f
    private var valExp //6
            = 0f
    private var errVal //7
            = 0
    private var q1: Boolean  //8
            = false
    private var q2: Boolean  //9
            = false
    private var q3: Boolean  //10
            = false
    private var q4: Boolean  //11
            = false

    init {
        date = Date().toString()
        manual = true
        inLab = true
        valTeo = 0f
        valExp = 0f
        errVal = 0
        q1 = false
        this.q2 = false
        this.q3 = false
        this.q4 = false
    }


    open fun getpId(): String? {
        return pId
    }

    open fun getTypeP(): LabType? {
        return typeP
    }

    open fun getDate(): String? {
        return date
    }

    open fun isManual(): Boolean {
        return manual
    }

    open fun isInLab(): Boolean {
        return inLab
    }

    open fun getValTeo(): Float {
        return valTeo
    }

    open fun getValExp(): Float {
        return valExp
    }

    open fun getErrVal(): Int {
        return errVal
    }

    open fun isQ1(): Boolean {
        return q1
    }

    open fun isQ2(): Boolean {
        return q2
    }

    open fun isQ3(): Boolean {
        return q3
    }

    open fun isQ4(): Boolean {
        return q4
    }

    open fun setTypeP(tipo: LabType?) {
        typeP = tipo
    }

    open fun setDate(date: String?) {
        this.date = date
    }

    open fun setManual(manual: Boolean) {
        this.manual = manual
    }

    open fun setInLab(inLab: Boolean) {
        this.inLab = inLab
    }

    protected open fun setValTeo(valTeo: Float) {
        this.valTeo = valTeo
    }

    open fun setValExp(valExp: Float) {
        this.valExp = valExp
    }

    open fun setErrVal(errVal: Int) {
        this.errVal = errVal
    }

    open fun setQ1(q1: Boolean) {
        this.q1 = q1
    }

    open fun setQ2(q2: Boolean) {
        this.q2 = q2
    }

    open fun setQ3(q3: Boolean) {
        this.q3 = q3
    }

    open fun setQ4(q4: Boolean) {
        this.q4 = q4
    }

    protected abstract fun setData()

    abstract fun getData(): String?

    open fun getIntFromType(type: LabType?): Int {
        return when (type) {
            LabType.TORSION -> 1
            LabType.PANDEO -> 2
            LabType.FLEXION -> 3
            LabType.TRACCION -> 4
            else -> -1
        }
    }

    open fun getTypeFromInt(typeInt: Int): LabType? {
        return when (typeInt) {
            1 -> LabType.TORSION
            2 -> LabType.PANDEO
            3 -> LabType.FLEXION
            4 -> LabType.TRACCION
            else -> LabType.INVALID
        }
    }

}