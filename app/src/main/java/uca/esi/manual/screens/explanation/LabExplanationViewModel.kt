package uca.esi.manual.screens.explanation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.R
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.LabFactory
import uca.esi.manual.utils.ViewModelString

class LabExplanationViewModel(var userId: String, inLab: Boolean, labType: Int) : ViewModel() {

    var lab: BaseLab

    var introText: ViewModelString

    private val _eventButtonPress = MutableLiveData<Boolean>()
    val eventButtonPress: LiveData<Boolean>
        get() = _eventButtonPress

    init {
        val labFactory = LabFactory()
        lab = labFactory.createFromLabType(userId, labType)
        lab.isInLab = inLab
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