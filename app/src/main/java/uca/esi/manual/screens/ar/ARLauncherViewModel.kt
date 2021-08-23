package uca.esi.manual.screens.ar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.models.ARResourceIds
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.utils.ViewModelString

/**
 * A r launcher view model
 *
 * @property lab
 * @constructor
 *
 * @param arResourceIds
 */
class ARLauncherViewModel(
    var lab: BaseLab,
    arResourceIds: ARResourceIds

) : ViewModel() {

    private val _eventLaunchAR = MutableLiveData<Boolean>()
    val eventLaunchAR: LiveData<Boolean>
        get() = _eventLaunchAR

    val textMachine: ViewModelString
    val textPosition: ViewModelString
    val qrImageId: Int

    init {
        _eventLaunchAR.value = false
        textMachine = ViewModelString(arResourceIds.machineTextId)
        textPosition = ViewModelString(arResourceIds.positionTextId)
        qrImageId = arResourceIds.imageId
    }

    /**
     * On launch a r
     *
     */
    fun onLaunchAR() {
        _eventLaunchAR.value = true
    }

    /**
     * On launch a r complete
     *
     */
    fun onLaunchARComplete() {
        _eventLaunchAR.value = false
    }


}