package uca.esi.manual.screens.survey.likert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.models.Survey

/**
 * Likert survey view model
 *
 * @property survey
 * @constructor Create empty Likert survey view model
 */
class LikertSurveyViewModel(val survey: Survey) : ViewModel() {

    // Event variable for empty user data handler
    private val _eventEmptyData = MutableLiveData<Boolean>()
    val eventEmptyData: LiveData<Boolean>
        get() = _eventEmptyData

    // Event variable for correct user data handler
    private val _eventCorrectData = MutableLiveData<Boolean>()
    val eventCorrectData: LiveData<Boolean>
        get() = _eventCorrectData

    /**
     * On ease radio button press
     *
     * @param index
     */
    fun onEaseRadioButtonPress(index: Int) {
        survey.likert[0] = index
    }

    /**
     * On help radio button press
     *
     * @param index
     */
    fun onHelpRadioButtonPress(index: Int) {
        survey.likert[1] = index
    }

    /**
     * On understanding radio button press
     *
     * @param index
     */
    fun onUnderstandingRadioButtonPress(index: Int) {
        survey.likert[2] = index
    }

    /**
     * On u i radio button press
     *
     * @param index
     */
    fun onUIRadioButtonPress(index: Int) {
        survey.likert[3] = index
    }

    /**
     * On u x radio button press
     *
     * @param index
     */
    fun onUXRadioButtonPress(index: Int) {
        survey.likert[4] = index
    }

    /**
     * Check all answers
     *
     */
    fun checkAllAnswers() {
        if (survey.likert.all { i -> i != 0 }) {
            onCorrectData()
        } else {
            onEmptyData()
        }
    }

    /**
     * On empty data
     *
     */
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