package uca.esi.manual.screens.materials.weights

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.labs.BaseLab

/**
 * Weights view model factory
 *
 * @property lab
 * @constructor Create empty Weights view model factory
 */
class WeightsViewModelFactory(
    private val lab: BaseLab
) : ViewModelProvider.Factory {

    /**
     * Create
     *
     * @param T
     * @param modelClass
     * @return
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeightsViewModel::class.java)) {
            return WeightsViewModel(lab) as T
        }
        throw IllegalArgumentException("Unknown WeightsViewModel class")
    }
}