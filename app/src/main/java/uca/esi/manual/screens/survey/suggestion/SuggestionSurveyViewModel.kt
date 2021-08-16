package uca.esi.manual.screens.survey.suggestion

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.database.DatabaseHandler
import uca.esi.manual.models.Survey

class SuggestionSurveyViewModel(val survey: Survey) : ViewModel() {

    private val dbHandler = DatabaseHandler()

    private val _eventSendSurvey = MutableLiveData<Boolean>()
    val eventSendSurvey: LiveData<Boolean>
        get() = _eventSendSurvey

    private val _eventOverflow = MutableLiveData<Boolean>()
    val eventOverflow: LiveData<Boolean>
        get() = _eventOverflow

    fun setSuggestionText(s: Editable) {
        survey.suggestion = s.toString()
    }

    fun onButtonFinishPress() {
        if (survey.suggestion.length > 300) {
            onEventOverflow()
        } else {
            onEventSendSurvey()
        }
    }

    fun uploadSurvey() {
        dbHandler.uploadData(survey)
    }

    private fun onEventSendSurvey() {
        _eventSendSurvey.value = true
    }

    fun onEventSendSurveyComplete() {
        _eventSendSurvey.value = false
    }

    private fun onEventOverflow() {
        _eventOverflow.value = true
    }

    fun onEventOverflowComplete() {
        _eventOverflow.value = false
    }
}