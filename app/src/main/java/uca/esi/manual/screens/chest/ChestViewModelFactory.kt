package uca.esi.manual.screens.chest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.labs.BaseLab

/**
 * Chest view model factory
 *
 * @property lab
 * @constructor Create empty Chest view model factory
 */
class ChestViewModelFactory(private val lab: BaseLab) :
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
        if (modelClass.isAssignableFrom(ChestViewModel::class.java)) {
            return ChestViewModel(lab) as T
        }
        throw IllegalArgumentException("Unknown ChestViewModel class")
    }
}