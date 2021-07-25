package uca.esi.manual.screens.selection

import android.widget.RadioGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import uca.esi.manual.models.Constants

class LabSelectionViewModel(var userId: String) : ViewModel() {

    private val _inLab = MutableLiveData<Boolean>()
    val inLab: LiveData<Boolean>
        get() = _inLab

    // Empty place selection event handler
    private val _eventEmptyData = MutableLiveData<Boolean>()
    val eventEmptyData: LiveData<Boolean>
        get() = _eventEmptyData

    // Torsion button press event handler
    private val _eventTorsionPress = MutableLiveData<Boolean>()
    val eventTorsionPress: LiveData<Boolean>
        get() = _eventTorsionPress

    // Pandeo button press event handler
    private val _eventPandeoPress = MutableLiveData<Boolean>()
    val eventPandeoPress: LiveData<Boolean>
        get() = _eventPandeoPress

    // Traccion button press event handler
    private val _eventTraccionPress = MutableLiveData<Boolean>()
    val eventTraccionPress: LiveData<Boolean>
        get() = _eventTraccionPress

    // Flexion button press event handler
    private val _eventFlexionPress = MutableLiveData<Boolean>()
    val eventFlexionPress: LiveData<Boolean>
        get() = _eventFlexionPress

    init {
        _inLab.value = true
    }

    fun checkPlaceSelectedNotEmpty(): Boolean {
        return if (_inLab.value == null) {
            Timber.w("Place not selected")
            onEmptyData()
            false
        } else {
            true
        }
    }

    fun onSplitTypeChanged(rg: RadioGroup, id: Int) {
        _inLab.value = id == Constants.IN_LAB_RADIO_INDEX
    }

    private fun onEmptyData() {
        _eventEmptyData.value = true
    }

    fun onEmptyDataComplete() {
        _eventEmptyData.value = false
    }

    fun onTorsionClick() {
        _eventTorsionPress.value = true
    }

    fun onTorsionClickComplete() {
        _eventTorsionPress.value = false
    }

    fun onPandeoClick() {
        _eventPandeoPress.value = true
    }

    fun onPandeoClickComplete() {
        _eventPandeoPress.value = false
    }

    fun onTraccionClick() {
        _eventTraccionPress.value = true
    }

    fun onTraccionClickComplete() {
        _eventTraccionPress.value = false
    }

    fun onFlexionClick() {
        _eventFlexionPress.value = true
    }

    fun onFlexionClickComplete() {
        _eventFlexionPress.value = false
    }
}