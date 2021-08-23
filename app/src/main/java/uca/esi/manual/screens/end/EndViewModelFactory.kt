package uca.esi.manual.screens.end

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * End view model factory
 *
 * @property allCorrect
 * @property surveyDone
 * @constructor Create empty End view model factory
 */
class EndViewModelFactory(private var allCorrect: Boolean, private val surveyDone: Boolean) :
    ViewModelProvider.Factory {

    /**
     * Create
     *
     * @param T
     * @param modelClass
     * @return
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EndViewModel::class.java)) {
            return EndViewModel(allCorrect, surveyDone) as T
        }
        throw IllegalArgumentException("Unknown EndViewModel class")
    }
}