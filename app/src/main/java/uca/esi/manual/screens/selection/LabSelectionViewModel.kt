package uca.esi.manual.screens.selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

/**
 * Lab selection view model
 *
 * @property userId
 * @constructor Create empty Lab selection view model
 */
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

    /**
     * Check place selected not empty
     *
     * @return
     */
    fun checkPlaceSelectedNotEmpty(): Boolean {
        return if (_inLab.value == null) {
            Timber.w("Place not selected")
            onEmptyData()
            false
        } else {
            true
        }
    }

    /**
     * On click in lab
     *
     */
    fun onClickInLab() {
        _inLab.value = true
    }

    /**
     * On click outside
     *
     */
    fun onClickOutside() {
        _inLab.value = false
    }

    /**
     * On empty data
     *
     */
    private fun onEmptyData() {
        _eventEmptyData.value = true
    }

    /**
     * On empty data complete
     *
     */
    fun onEmptyDataComplete() {
        _eventEmptyData.value = false
    }

    /**
     * On torsion click
     *
     */
    fun onTorsionClick() {
        _eventTorsionPress.value = true
    }

    /**
     * On torsion click complete
     *
     */
    fun onTorsionClickComplete() {
        _eventTorsionPress.value = false
    }

    /**
     * On pandeo click
     *
     */
    fun onPandeoClick() {
        _eventPandeoPress.value = true
    }

    /**
     * On pandeo click complete
     *
     */
    fun onPandeoClickComplete() {
        _eventPandeoPress.value = false
    }

    /**
     * On traccion click
     *
     */
    fun onTraccionClick() {
        _eventTraccionPress.value = true
    }

    /**
     * On traccion click complete
     *
     */
    fun onTraccionClickComplete() {
        _eventTraccionPress.value = false
    }

    /**
     * On flexion click
     *
     */
    fun onFlexionClick() {
        _eventFlexionPress.value = true
    }

    /**
     * On flexion click complete
     *
     */
    fun onFlexionClickComplete() {
        _eventFlexionPress.value = false
    }
}