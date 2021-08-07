package uca.esi.manual.screens.calcs.calculation.pandeo

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.utils.valueNotInThreshold

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

    private fun getDefaultLoadValue(lab: BaseLab): String {
        return if (lab.isInLab) {
            ""
        } else {
            lab.valTeo.toString()
        }
    }

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

    fun setLoadValue(s: Editable) {
        if (s.toString().isNotEmpty()) {
            _valueLoad.value = s.toString()
        } else {
            _valueLoad.value = ""
        }
    }

    //------------------Event handlers ------------------------
    private fun onEmptyData() {
        _eventEmptyData.value = true
    }

    fun onEmptyDataComplete() {
        _eventEmptyData.value = false
    }

    private fun onWrongData() {
        _eventWrongData.value = true
    }

    fun onWrongDataComplete() {
        _eventWrongData.value = false
    }

    private fun onCorrectData() {
        _eventCorrectData.value = true
    }

    fun onCorrectDataComplete() {
        _eventCorrectData.value = false
    }

    private fun onAutocompleteData() {
        _eventAutocompletedData.value = true
    }

    fun onAutocompleteDataComplete() {
        _eventAutocompletedData.value = false
    }
}