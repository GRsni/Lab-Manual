package uca.esi.manual.screens.ar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.R
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.PandeoLab
import uca.esi.manual.models.labs.TorsionLab
import uca.esi.manual.screens.ar.repository.ARModule
import uca.esi.manual.screens.ar.repository.ARResourceIds
import uca.esi.manual.utils.ViewModelString

class ARLauncherViewModel(
    labIN: BaseLab,
    arResourceIds: ARResourceIds
) : ViewModel() {

    private val _lab = MutableLiveData<BaseLab>()
    val lab: LiveData<BaseLab>
        get() = _lab

    private val _arModule = MutableLiveData<ARModule>()
    val arModule: LiveData<ARModule>
        get() = _arModule

    private val _eventLaunchAR = MutableLiveData<Boolean>()
    val eventLaunchAR: LiveData<Boolean>
        get() = _eventLaunchAR

    val textMachine: ViewModelString
    val textPosition: ViewModelString
    val qrImageId: Int

    init {
        _lab.value = labIN
        _arModule.value = ARModule(executed = false, solved = false)
        _eventLaunchAR.value = false
        textMachine = ViewModelString(arResourceIds.machineTextId)
        textPosition = ViewModelString(arResourceIds.positionTextId)
        qrImageId = arResourceIds.imageId
    }

    private fun getMachineText(lab: BaseLab): ViewModelString {
        return when (lab) {
            is TorsionLab -> {
                if (lab.isInLab) {
                    ViewModelString(R.string.prepara_maquina_torsion_lab)
                } else {
                    ViewModelString(R.string.prepara_maquina_torsion_casa)
                }
            }
            is PandeoLab -> {
                if (lab.isInLab) {
                    ViewModelString(R.string.prepara_maquina_pandeo_lab)
                } else {
                    ViewModelString(R.string.prepara_maquina_pandeo_casa)
                }
            }
            else -> ViewModelString(R.string.error_datos_practicas)
        }
    }

    fun onLaunchAR() {
        _eventLaunchAR.value = true
        _arModule.value!!.executed = true
    }

    fun onLaunchARComplete() {
        _eventLaunchAR.value = false
    }


}