package uca.esi.manual.screens.survey.suggestion

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.database.DatabaseHandler
import uca.esi.manual.models.Survey

/**
 * Suggestion survey view model
 *
 * @property survey
 * @constructor Create empty Suggestion survey view model
 */
class SuggestionSurveyViewModel(val survey: Survey) : ViewModel() {

    private val dbHandler = DatabaseHandler()

    private val _eventSendSurvey = MutableLiveData<Boolean>()
    val eventSendSurvey: LiveData<Boolean>
        get() = _eventSendSurvey

    private val _eventOverflow = MutableLiveData<Boolean>()
    val eventOverflow: LiveData<Boolean>
        get() = _eventOverflow

    /**
     * Set suggestion text
     *
     * @param s
     */
    fun setSuggestionText(s: Editable) {
        survey.suggestion = s.toString()
    }

    /**
     * On button finish press
     *
     */
    fun onButtonFinishPress() {
        if (survey.suggestion.length > 300) {
            onEventOverflow()
        } else {
            onEventSendSurvey()
        }
    }

    /**
     * Upload survey
     *
     */
    fun uploadSurvey() {
        dbHandler.uploadData(survey)
    }

    /**
     * On event send survey
     *
     */
    private fun onEventSendSurvey() {
        _eventSendSurvey.value = true
    }

    /**
     * On event send survey complete
     *
     */
    fun onEventSendSurveyComplete() {
        _eventSendSurvey.value = false
    }

    /**
     * On event overflow
     *
     */
    private fun onEventOverflow() {
        _eventOverflow.value = true
    }

    /**
     * On event overflow complete
     *
     */
    fun onEventOverflowComplete() {
        _eventOverflow.value = false
    }
}