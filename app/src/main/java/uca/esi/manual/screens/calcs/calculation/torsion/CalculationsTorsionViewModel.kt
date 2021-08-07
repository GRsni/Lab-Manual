package uca.esi.manual.screens.calcs.calculation.torsion

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.TorsionLab
import uca.esi.manual.utils.valueNotInThreshold

class CalculationsTorsionViewModel(labIN: BaseLab) : ViewModel() {
    private val _lab = MutableLiveData<BaseLab>()
    val lab: LiveData<BaseLab>
        get() = _lab

    private val _valueAmpli = MutableLiveData<String>()
    val valueAmpli: LiveData<String>
        get() = _valueAmpli

    private var errorAmpli = false

    private val _valueMoment = MutableLiveData<String>()
    val valueMoment: LiveData<String>
        get() = _valueMoment

    private var errorMoment = false

    // Event variable for empty user data handler
    private val _eventEmptyData = MutableLiveData<Boolean>()
    val eventEmptyData: LiveData<Boolean>
        get() = _eventEmptyData

    // Event variable for wrong user data handler
    private val _eventWrongAmpliData = MutableLiveData<Boolean>()
    val eventWrongAmpliData: LiveData<Boolean>
        get() = _eventWrongAmpliData

    // Event variable for wrong user data handler
    private val _eventWrongMomentData = MutableLiveData<Boolean>()
    val eventWrongMomentData: LiveData<Boolean>
        get() = _eventWrongMomentData

    // Event variable for correct user data handler
    private val _eventCorrectData = MutableLiveData<Boolean>()
    val eventCorrectData: LiveData<Boolean>
        get() = _eventCorrectData

    private val _eventAutocompletedData = MutableLiveData<Boolean>()
    val eventAutocompleteData: LiveData<Boolean>
        get() = _eventAutocompletedData


    init {
        _lab.value = labIN
        _valueMoment.value = getDefaultMomentValue(labIN)
        _valueAmpli.value = getDefaultAmpliValue(labIN)
        if (!labIN.isInLab) {
            onAutocompleteData()
        }
    }

    private fun getDefaultMomentValue(lab: BaseLab): String {
        return if (lab.isInLab) {
            ""
        } else {
            lab.valTeo.toString()
        }
    }

    private fun getDefaultAmpliValue(lab: BaseLab): String {
        return if (lab.isInLab) {
            ""
        } else {
            (lab as TorsionLab).ampliTeo.toString()
        }
    }

    fun checkValues() {
        val torsionLab = _lab.value as TorsionLab
        if (!_valueMoment.value.isNullOrEmpty() && !_valueAmpli.value.isNullOrEmpty()) {
            if (valueNotInThreshold(
                    torsionLab.ampliTeo,
                    _valueAmpli.value!!.toFloat(),
                    10f
                )
                && !errorAmpli
            ) {
                errorAmpli = true
                onWrongAmpliData()
            } else if (valueNotInThreshold(
                    torsionLab.valTeo,
                    _valueMoment.value!!.toFloat(),
                    5f
                )
                && !errorMoment
            ) {
                errorMoment = true
                torsionLab.errVal += 1
                onWrongMomentData()
            } else {
                torsionLab.ampliExp = _valueAmpli.value!!.toFloat()
                torsionLab.valExp = _valueMoment.value!!.toFloat()
                onCorrectData()
            }
        } else {
            onEmptyData()
        }
    }


    fun setAmpliValue(s: Editable) {
        if (s.toString().isNotEmpty()) {
            _valueAmpli.value = s.toString()
        } else {
            _valueAmpli.value = ""
        }
    }

    fun setMomentValue(s: Editable) {
        if (s.toString().isNotEmpty()) {
            _valueMoment.value = s.toString()
        } else {
            _valueAmpli.value = ""
        }
    }

    //------------------Event handlers ------------------------
    private fun onEmptyData() {
        _eventEmptyData.value = true
    }

    fun onEmptyDataComplete() {
        _eventEmptyData.value = false
    }

    private fun onWrongAmpliData() {
        _eventWrongAmpliData.value = true
    }

    fun onWrongAmpliDataComplete() {
        _eventWrongAmpliData.value = false
    }

    private fun onWrongMomentData() {
        _eventWrongMomentData.value = true
    }

    fun onWrongMomentDataComplete() {
        _eventWrongMomentData.value = false
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