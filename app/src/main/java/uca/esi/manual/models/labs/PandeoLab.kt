package uca.esi.manual.models.labs

class PandeoLab(pId: String?) : BaseLab(pId, LabType.PANDEO) {
    private val APOYOS = arrayOf("AA", "AE", "EE")
    private val BARRAS_PANDEO = floatArrayOf(500f, 1000f)
    private val CARGAS_TEO_PANDEO = floatArrayOf(883.7f, 1803f, 3535f, 220.9f, 450.8f, 883.7f)

    private var bar = 0f
    private var errBar = 0
    private var fixtures: String? = null
    private var errFixtures = 0


    init {
        setData()
    }

    override fun setData() {
        val randCargaTeo = (Math.random() * CARGAS_TEO_PANDEO.size).toInt()
        val randSoporteIndex = (Math.random() * APOYOS.size).toInt()
        val randBarrasIndex = (Math.random() * BARRAS_PANDEO.size).toInt()
        bar = BARRAS_PANDEO[randBarrasIndex]
        fixtures = APOYOS[randSoporteIndex]
        setValTeo(CARGAS_TEO_PANDEO[randCargaTeo])
    }

    override fun getData(): String {
        return "$fixtures:$bar"
    }

    fun getBar(): Float {
        return bar
    }

    fun setBar(bar: Float) {
        this.bar = bar
    }

    fun getErrBar(): Int {
        return errBar
    }

    fun setErrBar(errBar: Int) {
        this.errBar = errBar
    }

    fun getFixtures(): String? {
        return fixtures
    }

    fun setFixtures(fixtures: String?) {
        this.fixtures = fixtures
    }

    fun getErrFixtures(): Int {
        return errFixtures
    }

    fun setErrFixtures(errFixtures: Int) {
        this.errFixtures = errFixtures
    }
}