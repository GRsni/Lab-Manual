package uca.esi.manual.screens.calcs.calculation.pandeo

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.utils.valueNotInThreshold

/**
 * Calculations pandeo view model
 *
 * @property lab
 * @constructor Create empty Calculations pandeo view model
 */
class CalculationsPandeoViewModel(var lab: BaseLab) : ViewModel() {
    private val _valueLoad = MutableLiveData<String>()
    val valueLoad: LiveData<String>
        get() = _valueLoad

    private var errorLoad = false

    // Event variable for empty user data handler
    private val _eventEmptyData = MutableLiveData<Boolean>()
    val eventEmptyData: LiveData<Boolean>
        get() = _eventEmptyData

    // Event variable for wrong user data handler
    private val _eventWrongData = MutableLiveData<Boolean>()
    val eventWrongData: LiveData<Boolean>
        get() = _eventWrongData

    // Event variable for correct user data handler
    private val _eventCorrectData = MutableLiveData<Boolean>()
    val eventCorrectData: LiveData<Boolean>
        get() = _eventCorrectData

    private val _eventAutocompletedData = MutableLiveData<Boolean>()
    val eventAutocompleteData: LiveData<Boolean>
        get() = _eventAutocompletedData


    init {
        _valueLoad.value = getDefaultLoadValue(lab)
        if (!lab.isInLab) {
            onAutocompleteData()
        }
    }

    /**
     * Get default load value
     *
     * @param lab
     * @return
     */
    private fun getDefaultLoadValue(lab: BaseLab): String {
        return if (lab.isInLab) {
            ""
        } else {
            lab.valTeo.toString()
        }
    }

    /**
     * Check value
     *
     */
    fun checkValue() {
        if (!_valueLoad.value.isNullOrEmpty()) {
            if (valueNotInThreshold(
                    lab.valTeo,
                    _valueLoad.value!!.toFloat(),
                    5f
                ) && !errorLoad
            ) {
                errorLoad = true
                lab.errVal += 1
                onWrongData()
            } else {
                lab.valExp = _valueLoad.value!!.toFloat()
                onCorrectData()
            }
        } else {
            onEmptyData()
        }
    }

    /**
     * Set load value
     *
     * @param s
     */
    fun setLoadValue(s: Editable) {
        if (s.toString().isNotEmpty()) {
            _valueLoad.value = s.toString()
        } else {
            _valueLoad.value = ""
        }
    }

    /**
     * On empty data
     *
     *///------------------Event handlers ------------------------
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
     * On wrong data
     *
     */
    private fun onWrongData() {
        _eventWrongData.value = true
    }

    /**
     * On wrong data complete
     *
     */
    fun onWrongDataComplete() {
        _eventWrongData.value = false
    }

    /**
     * On correct data
     *
     */
    private fun onCorrectData() {
        _eventCorrectData.value = true
    }

    /**
     * On correct data complete
     *
     */
    fun onCorrectDataComplete() {
        _eventCorrectData.value = false
    }

    /**
     * On autocomplete data
     *
     */
    private fun onAutocompleteData() {
        _eventAutocompletedData.value = true
    }

    /**
     * On autocomplete data complete
     *
     */
    fun onAutocompleteDataComplete() {
        _eventAutocompletedData.value = false
    }
}