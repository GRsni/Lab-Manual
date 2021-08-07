package uca.esi.manual.screens.materials.pandeo

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.models.Constants
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.PandeoLab
import uca.esi.manual.screens.materials.MaterialsI

class MaterialsPandeoViewModel(var lab: BaseLab) : ViewModel(), MaterialsI {

    private val _barSelected500 = MutableLiveData<Boolean>()
    val barSelected500: LiveData<Boolean>
        get() = _barSelected500

    private val _fixedJoints = MutableLiveData<String>()
    val fixedJoints: LiveData<String>
        get() = _fixedJoints

    var fixedJointsEdited = false

    private val _looseJoints = MutableLiveData<String>()
    val looseJoints: LiveData<String>
        get() = _looseJoints

    var looseJointsEdited = false

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

    fun onClickBar500() {
        _barSelected500.value = true
    }

    fun onClickBar1000() {
        _barSelected500.value = false
    }

    fun setFixedJoints(s: Editable) {
        _fixedJoints.value = s.toString()
    }

    fun setLooseJoints(s: Editable) {
        _looseJoints.value = s.toString()
    }

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

    //------------------Event handlers ------------------------
    private fun onEmptyData() {
        _eventEmptyData.value = true
    }

    fun onEmptyDataComplete() {
        _eventEmptyData.value = false
    }

    private fun onWrongBarData() {
        _eventWrongBarData.value = true
    }

    fun onWrongDataBarComplete() {
        _eventWrongBarData.value = false
    }

    private fun onWrongJointData() {
        _eventWrongJointData.value = true
    }

    fun onWrongDataJointComplete() {
        _eventWrongJointData.value = false
    }

    private fun onCorrectData() {
        _eventCorrectData.value = true
    }

    fun onCorrectDataComplete() {
        _eventCorrectData.value = false
    }
}