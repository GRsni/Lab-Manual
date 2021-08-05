package uca.esi.manual.screens.chest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.R
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.PandeoLab
import uca.esi.manual.utils.ViewModelString

class ChestViewModel(labIN: BaseLab) : ViewModel() {
    private val _lab = MutableLiveData<BaseLab>()
    val lab: LiveData<BaseLab>
        get() = _lab

    private val _eventButtonPressed = MutableLiveData<Boolean>()
    val eventButtonPressed: LiveData<Boolean>
        get() = _eventButtonPressed

    var materialsText: ViewModelString

    var chestImage: Int

    init {
        _lab.value = labIN
        materialsText = getIntroText(_lab.value!!.labType)
        chestImage = getChestImage(_lab.value!!.labType)
    }

    private fun getIntroText(labType: BaseLab.LabType): ViewModelString {
        return when (labType) {
            BaseLab.LabType.TORSION ->
                ViewModelString(R.string.manual_materiales_torsion)
            BaseLab.LabType.PANDEO -> {
                val bar = (lab.value as PandeoLab).bar
                val fixturesList: Array<String> =
                    getFixturesStringList((lab.value as PandeoLab).fixtures)
                ViewModelString(
                    R.string.manual_materiales_pandeo,
                    arrayListOf(
                        bar,
                        fixturesList[0],
                        fixturesList[1]
                    )
                )
            }
            else -> ViewModelString(R.string.error_datos_practicas)
        }
    }

    private fun getFixturesStringList(fixturesString: String): Array<String> {
        return Array(2) { i ->
            when (fixturesString[i]) {
                'A' -> "Articulado"
                'E' -> "Empotrado"
                else -> throw IllegalArgumentException("Unknown fixture type")
            }
        }
    }

    private fun getChestImage(labType: BaseLab.LabType): Int {
        return when (labType) {
            BaseLab.LabType.TORSION -> R.drawable.materiales
            BaseLab.LabType.PANDEO -> R.drawable.portico_pandeo_small
            else -> throw java.lang.IllegalArgumentException("Unknown labType")
        }
    }

    fun onButtonPressed() {
        _eventButtonPressed.value = true
    }

    fun onButtonPressedComplete() {
        _eventButtonPressed.value = false
    }
}