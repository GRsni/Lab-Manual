package uca.esi.manual.screens.explanation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.R
import uca.esi.manual.utils.ViewModelString
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.LabFactory

class LabExplanationViewModel(var userId: String, inLab: Boolean, labType: Int) : ViewModel() {

    private val _lab = MutableLiveData<BaseLab>()
    val lab: LiveData<BaseLab>
        get() = _lab

    var introText: ViewModelString

    private val _eventButtonPress = MutableLiveData<Boolean>()
    val eventButtonPress: LiveData<Boolean>
        get() = _eventButtonPress

    init {
        val labFactory = LabFactory()
        _lab.value = labFactory.createFromLabType(userId, labType)
        _lab.value!!.isInLab = inLab
        introText = getIntroText(labType)
    }

    private fun getIntroText(labType: Int): ViewModelString {
        return ViewModelString(
            R.string.manual_introduccion,
            BaseLab.getTypeFromInt(labType).toString()
        )
    }

    fun onButtonPress() {
        _eventButtonPress.value = true
    }

    fun onButtonPressComplete() {
        _eventButtonPress.value = false
    }

}