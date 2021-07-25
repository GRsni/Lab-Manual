package uca.esi.manual.models.labs

class TorsionLab(pId: String?) : BaseLab(pId, LabType.TORSION) {
    private val CARGAS_TORSION = intArrayOf(5, 10, 15, 20)
    private val MOMENTOS_TEO = floatArrayOf(.5f, 1.0f, 1.5f, 2.0f)
    private val AMPLI_TEO_TORSION = floatArrayOf(-.035f, -.073f, -.112f, -.15f)


    private var weights: String? = null
    private var errWeights = 0
    private var ampliTeo = 0f
    private var ampliExp = 0f

    init {
        setData()
    }

    override fun setData() {
        val index = (Math.random() * CARGAS_TORSION.size).toInt()
        weights = CARGAS_TORSION[index].toString()
        setValTeo(MOMENTOS_TEO[index])
        ampliTeo = AMPLI_TEO_TORSION[index]
    }

    override fun getData(): String? {
        return getWeights()
    }

    private fun getWeights(): String? {
        return weights
    }

    fun setWeights(weights: String?) {
        this.weights = weights
    }

    fun getErrWeights(): Int {
        return errWeights
    }

    fun setErrWeights(errWeights: Int) {
        this.errWeights = errWeights
    }

    fun getAmpliTeo(): Float {
        return ampliTeo
    }

    fun getAmpliExp(): Float {
        return ampliExp
    }

    fun setAmpliExp(ampliExp: Float) {
        this.ampliExp = ampliExp
    }
}