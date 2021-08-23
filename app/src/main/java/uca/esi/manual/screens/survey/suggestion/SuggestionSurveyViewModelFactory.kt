package uca.esi.manual.screens.survey.suggestion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.Survey

/**
 * Suggestion survey view model factory
 *
 * @property survey
 * @constructor Create empty Suggestion survey view model factory
 */
class SuggestionSurveyViewModelFactory(private val survey: Survey) : ViewModelProvider.Factory {

    /**
     * Create
     *
     * @param T
     * @param modelClass
     * @return
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuggestionSurveyViewModel::class.java)) {
            return SuggestionSurveyViewModel(survey) as T
        }
        throw IllegalArgumentException("Unknown SuggestionSuveyViewModel class")
    }
}