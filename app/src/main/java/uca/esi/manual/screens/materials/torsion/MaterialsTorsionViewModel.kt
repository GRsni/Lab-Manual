package uca.esi.manual.screens.materials.torsion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.TorsionLab
import uca.esi.manual.screens.materials.MaterialsI

class MaterialsTorsionViewModel(labIN: BaseLab) : ViewModel(), MaterialsI{

    private val _lab = MutableLiveData<BaseLab>()
    val lab: LiveData<BaseLab>
        get() = _lab

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

    private val checkboxes = Array(8) { false }

    init {
        _lab.value = labIN
        _eventCorrectData.value = false
        _eventEmptyData.value = false
        _eventWrongData.value = false
    }

    override fun checkMaterials() {
        if (checkCheckboxesAreCorrect()) {
            onCorrectData()
        } else {
            if (!_eventEmptyData.value!!) {
                onWrongData()
            }
        }
    }

    private fun checkCheckboxesAreCorrect(): Boolean {
        return !checkboxes[0] && checkboxes[1] && !checkboxes[2] && checkboxes[3]
                && !checkboxes[4] && !checkboxes[5] && checkboxes[6] && checkboxes[7]
    }

    fun onCheckBoxClicked(id: Int, value: Boolean) {
        checkboxes[id] = value
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
        (_lab.value as TorsionLab).errWeights += 1
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