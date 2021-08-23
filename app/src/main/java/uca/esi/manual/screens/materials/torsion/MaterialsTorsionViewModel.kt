package uca.esi.manual.screens.materials.torsion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.TorsionLab
import uca.esi.manual.screens.materials.MaterialsI

/**
 * Materials torsion view model
 *
 * @property lab
 * @constructor Create empty Materials torsion view model
 */
class MaterialsTorsionViewModel(var lab: BaseLab) : ViewModel(), MaterialsI {

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
        _eventCorrectData.value = false
        _eventEmptyData.value = false
        _eventWrongData.value = false
    }

    /**
     * Check materials
     *
     */
    override fun checkMaterials() {
        if (checkCheckboxesAreCorrect()) {
            onCorrectData()
        } else {
            if (!_eventEmptyData.value!!) {
                onWrongData()
                (lab as TorsionLab).errWeights += 1
            }
        }
    }

    /**
     * Check checkboxes are correct
     *
     * @return
     */
    private fun checkCheckboxesAreCorrect(): Boolean {
        return !checkboxes[0] && checkboxes[1] && !checkboxes[2] && checkboxes[3]
                && !checkboxes[4] && !checkboxes[5] && checkboxes[6] && checkboxes[7]
    }

    /**
     * On check box clicked
     *
     * @param id
     * @param value
     */
    fun onCheckBoxClicked(id: Int, value: Boolean) {
        checkboxes[id] = value
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

}