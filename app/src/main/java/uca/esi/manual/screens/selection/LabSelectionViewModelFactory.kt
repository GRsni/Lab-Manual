package uca.esi.manual.screens.selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Lab selection view model factory
 *
 * @property userId
 * @constructor Create empty Lab selection view model factory
 */
class LabSelectionViewModelFactory(private val userId: String) : ViewModelProvider.Factory {

    /**
     * Create
     *
     * @param T
     * @param modelClass
     * @return
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LabSelectionViewModel::class.java)) {
            return LabSelectionViewModel(userId) as T
        }
        throw IllegalArgumentException("Unknown LabSelectionViewModel class")
    }
}