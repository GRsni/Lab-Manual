package uca.esi.manual.screens.materials.pandeo

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.models.Constants
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.PandeoLab
import uca.esi.manual.screens.materials.MaterialsI

/**
 * Materials pandeo view model
 *
 * @property lab
 * @constructor Create empty Materials pandeo view model
 */
class MaterialsPandeoViewModel(var lab: BaseLab) : ViewModel(), MaterialsI {

    private val _barSelected500 = MutableLiveData<Boolean>()
    val barSelected500: LiveData<Boolean>
        get() = _barSelected500

    private val _fixedJoints = MutableLiveData<String>()

    private val _looseJoints = MutableLiveData<String>()

    // Event variable for empty user data handler
    private val _eventEmptyData = MutableLiveData<Boolean>()
    val eventEmptyData: LiveData<Boolean>
        get() = _eventEmptyData

    // Event variable for wrong user data handler
    private val _eventWrongBarData = MutableLiveData<Boolean>()
    val eventWrongBarData: LiveData<Boolean>
        get() = _eventWrongBarData

    // Event variable for wrong user data handler
    private val _eventWrongJointData = MutableLiveData<Boolean>()
    val eventWrongJointData: LiveData<Boolean>
        get() = _eventWrongJointData

    // Event variable for correct user data handler
    private val _eventCorrectData = MutableLiveData<Boolean>()
    val eventCorrectData: LiveData<Boolean>
        get() = _eventCorrectData


    init {
        _barSelected500.value = true
        _looseJoints.value = "0"
        _fixedJoints.value = "0"

        _eventCorrectData.value = false
        _eventEmptyData.value = false
        _eventWrongBarData.value = false
        _eventWrongJointData.value = false
    }

    /**
     * On click bar500
     *
     */
    fun onClickBar500() {
        _barSelected500.value = true
    }

    /**
     * On click bar1000
     *
     */
    fun onClickBar1000() {
        _barSelected500.value = false
    }

    /**
     * Set fixed joints
     *
     * @param s
     */
    fun setFixedJoints(s: Editable) {
        _fixedJoints.value = s.toString()
    }

    /**
     * Set loose joints
     *
     * @param s
     */
    fun setLooseJoints(s: Editable) {
        _looseJoints.value = s.toString()
    }

    /**
     * Check materials
     *
     */
    override fun checkMaterials() {
        if (checkBarIsCorrect()) {
            if (checkJoints()) {
                onCorrectData()
            } else {
                onWrongJointData()
                (lab as PandeoLab).errFixtures += 1
            }
        } else {
            if (!_eventEmptyData.value!!) {
                onWrongBarData()
                (lab as PandeoLab).errBar += 1
            }
        }
    }

    /**
     * Check bar is correct
     *
     * @return
     */
    private fun checkBarIsCorrect(): Boolean {
        val labBar = (lab as PandeoLab).bar

        return if (_barSelected500.value != null) {
            if (labBar == Constants.BAR_500_VALUE) {
                _barSelected500.value!!
            } else {
                !_barSelected500.value!!
            }
        } else {
            onEmptyData()
            false
        }
    }

    /**
     * Check joints
     *
     * @return
     */
    private fun checkJoints(): Boolean {
        val joints = countLabJoints((lab as PandeoLab).fixtures)
        val looseJointsLab = joints[0]
        val fixedJointsLab = joints[1]
        return if (_looseJoints.value != null && _fixedJoints.value != null) {
            looseJointsLab == _looseJoints.value!!.toInt() && fixedJointsLab == _fixedJoints.value!!.toInt()
        } else {
            onEmptyData()
            false
        }

    }

    /**
     * Count lab joints
     *
     * @param s
     * @return
     */
    private fun countLabJoints(s: String): IntArray {
        val joints = IntArray(2)
        for (i in s.indices) {
            if (s[i] == 'A') {
                joints[0]++
            } else if (s[i] == 'E') {
                joints[1]++
            }
        }
        return joints
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
     * On wrong bar data
     *
     */
    private fun onWrongBarData() {
        _eventWrongBarData.value = true
    }

    /**
     * On wrong data bar complete
     *
     */
    fun onWrongDataBarComplete() {
        _eventWrongBarData.value = false
    }

    /**
     * On wrong joint data
     *
     */
    private fun onWrongJointData() {
        _eventWrongJointData.value = true
    }

    /**
     * On wrong data joint complete
     *
     */
    fun onWrongDataJointComplete() {
        _eventWrongJointData.value = false
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