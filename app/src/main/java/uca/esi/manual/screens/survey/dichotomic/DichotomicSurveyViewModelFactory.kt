package uca.esi.manual.screens.survey.dichotomic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.Survey

/**
 * Dichotomic survey view model factory
 *
 * @property survey
 * @constructor Create empty Dichotomic survey view model factory
 */
class DichotomicSurveyViewModelFactory(private val survey: Survey) : ViewModelProvider.Factory {

    /**
     * Create
     *
     * @param T
     * @param modelClass
     * @return
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DichotomicSurveyViewModel::class.java)) {
            return DichotomicSurveyViewModel(survey) as T
        }
        throw IllegalArgumentException("Unknown DichotomicSurveyViewModel class")
    }
}