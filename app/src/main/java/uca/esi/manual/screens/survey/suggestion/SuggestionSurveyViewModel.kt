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

    fun setSuggestionText(s: Editable) {
        survey.suggestion = s.toString()
    }

    fun onButtonFinishPress() {
        onEventSendSurvey()
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
}