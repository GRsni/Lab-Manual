package uca.esi.manual.screens.survey.likert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import uca.esi.manual.models.Survey

class LikertSurveyViewModel(val survey: Survey) : ViewModel() {

    // Event variable for empty user data handler
    private val _eventEmptyData = MutableLiveData<Boolean>()
    val eventEmptyData: LiveData<Boolean>
        get() = _eventEmptyData

    // Event variable for correct user data handler
    private val _eventCorrectData = MutableLiveData<Boolean>()
    val eventCorrectData: LiveData<Boolean>
        get() = _eventCorrectData

    init {
        survey.likert = IntArray(5) { 0 }
    }


    fun onEaseRadioButtonPress(index: Int) {
        survey.likert[0] = index
    }

    fun onHelpRadioButtonPress(index: Int) {
        survey.likert[1] = index
    }

    fun onUnderstandingRadioButtonPress(index: Int) {
        survey.likert[2] = index
    }

    fun onUIRadioButtonPress(index: Int) {
        survey.likert[3] = index
    }

    fun onUXRadioButtonPress(index: Int) {
        survey.likert[4] = index
    }

    fun checkAllAnswers() {
        if (survey.likert.all { i -> i != 0 }) {
            onCorrectData()
        } else {
            onEmptyData()
        }
    }

    private fun onEmptyData() {
        _eventEmptyData.value = true
    }

    fun onEmptyDataComplete() {
        _eventEmptyData.value = false
    }

    private fun onCorrectData() {
        _eventCorrectData.value = true
    }

    fun onCorrectDataComplete() {
        _eventCorrectData.value = false
    }
}