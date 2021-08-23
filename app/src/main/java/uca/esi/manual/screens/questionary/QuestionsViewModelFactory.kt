package uca.esi.manual.screens.questionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.labs.BaseLab

/**
 * Questions view model factory
 *
 * @property lab
 * @constructor Create empty Questions view model factory
 */
class QuestionsViewModelFactory(private val lab: BaseLab) : ViewModelProvider.Factory {


    /**
     * Create
     *
     * @param T
     * @param modelClass
     * @return
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionsViewModel::class.java)) {
            return QuestionsViewModel(lab) as T
        }
        throw IllegalArgumentException("Unknown QuestionsViewModel class")
    }
}