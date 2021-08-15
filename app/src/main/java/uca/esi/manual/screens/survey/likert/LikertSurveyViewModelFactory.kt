package uca.esi.manual.screens.survey.likert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.Survey
import java.lang.IllegalArgumentException

class LikertSurveyViewModelFactory(private val survey: Survey): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LikertSurveyViewModel::class.java)){
            return LikertSurveyViewModel(survey) as T
        }
        throw IllegalArgumentException("Unknown LikertSurveyViewModel class")
    }
}