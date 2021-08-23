package uca.esi.manual.screens.calcs.calculation.torsion

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.TorsionLab
import uca.esi.manual.utils.valueNotInThreshold

/**
 * Calculations torsion view model
 *
 * @property lab
 * @constructor Create empty Calculations torsion view model
 */
class CalculationsTorsionViewModel(var lab: BaseLab) : ViewModel() {

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
        _valueMoment.value = getDefaultMomentValue(lab)
        _valueAmpli.value = getDefaultAmpliValue(lab)
        if (!lab.isInLab) {
            onAutocompleteData()
        }
    }

    /**
     * Get default moment value
     *
     * @param lab
     * @return
     */
    private fun getDefaultMomentValue(lab: BaseLab): String {
        return if (lab.isInLab) {
            ""
        } else {
            lab.valTeo.toString()
        }
    }

    /**
     * Get default ampli value
     *
     * @param lab
     * @return
     */
    private fun getDefaultAmpliValue(lab: BaseLab): String {
        return if (lab.isInLab) {
            ""
        } else {
            (lab as TorsionLab).ampliTeo.toString()
        }
    }

    /**
     * Check values
     *
     */
    fun checkValues() {
        val torsionLab = lab as TorsionLab
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


    /**
     * Set ampli value
     *
     * @param s
     */
    fun setAmpliValue(s: Editable) {
        if (s.toString().isNotEmpty()) {
            _valueAmpli.value = s.toString()
        } else {
            _valueAmpli.value = ""
        }
    }

    /**
     * Set moment value
     *
     * @param s
     */
    fun setMomentValue(s: Editable) {
        if (s.toString().isNotEmpty()) {
            _valueMoment.value = s.toString()
        } else {
            _valueAmpli.value = ""
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
     * On wrong ampli data
     *
     */
    private fun onWrongAmpliData() {
        _eventWrongAmpliData.value = true
    }

    /**
     * On wrong ampli data complete
     *
     */
    fun onWrongAmpliDataComplete() {
        _eventWrongAmpliData.value = false
    }

    /**
     * On wrong moment data
     *
     */
    private fun onWrongMomentData() {
        _eventWrongMomentData.value = true
    }

    /**
     * On wrong moment data complete
     *
     */
    fun onWrongMomentDataComplete() {
        _eventWrongMomentData.value = false
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