package uca.esi.manual.screens.explanation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.R
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.LabFactory
import uca.esi.manual.utils.ViewModelString

/**
 * Lab explanation view model
 *
 * @property userId
 * @constructor
 *
 * @param inLab
 * @param labType
 */
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

    /**
     * Get intro text
     *
     * @param labType
     * @return
     */
    private fun getIntroText(labType: Int): ViewModelString {
        return ViewModelString(
            R.string.manual_introduccion,
            BaseLab.getTypeFromInt(labType).toString()
        )
    }

    /**
     * On button press
     *
     */
    fun onButtonPress() {
        _eventButtonPress.value = true
    }

    /**
     * On button press complete
     *
     */
    fun onButtonPressComplete() {
        _eventButtonPress.value = false
    }

}