package uca.esi.manual.screens.materials.weights

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.R
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.TorsionLab
import uca.esi.manual.screens.materials.MaterialsI
import uca.esi.manual.utils.ViewModelString

class WeightsViewModel(var lab: BaseLab) : ViewModel(), MaterialsI {

    private val _support = MutableLiveData<Int>()
    val support: LiveData<Int>
        get() = _support

    private val _load5N = MutableLiveData<Int>()
    val load5N: LiveData<Int>
        get() = _load5N

    private val _load10N = MutableLiveData<Int>()
    val load10N: LiveData<Int>
        get() = _load10N

    private val _load20N = MutableLiveData<Int>()
    val load20N: LiveData<Int>
        get() = _load20N

    val loadText: ViewModelString

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

    init {
        _support.value = 1
        _load5N.value = 0
        _load10N.value = 0
        _load20N.value = 0
        loadText = ViewModelString(R.string.manual_torsion_carga)
    }

    fun setSupport(s: Editable) {
        if (s.toString().isNotEmpty()) {
            _support.value = s.toString().toInt()
        } else {
            _support.value = 0
        }
    }

    fun setLoad5N(s: Editable) {
        if (s.toString().isNotEmpty()) {
            _load5N.value = s.toString().toInt()
        } else {
            _load5N.value = 0
        }
    }

    fun setLoad10N(s: Editable) {
        if (s.toString().isNotEmpty()) {
            _load10N.value = s.toString().toInt()
        } else {
            _load10N.value = 0
        }
    }

    fun setLoad20N(s: Editable) {
        if (s.toString().isNotEmpty()) {
            _load20N.value = s.toString().toInt()
        } else {
            _load20N.value = 0
        }
    }

    override fun checkMaterials() {
        if (_support.value != 0) {
            if ((lab as TorsionLab).weights == getCombinedLoad()) {
                onCorrectData()
            } else {
                onWrongData()
                (lab as TorsionLab).errWeights += 1
            }
        } else {
            onEmptyData()
        }
    }

    private fun getCombinedLoad(): Int {
        return (_support.value!! * 5) + (_load5N.value!! * 5) +
                (_load10N.value!! * 10) + (_load20N.value!! * 20)
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
}