package uca.esi.manual.screens.calcs.calculation.pandeo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.labs.BaseLab

/**
 * Calculations pandeo view model factory
 *
 * @property lab
 * @constructor Create empty Calculations pandeo view model factory
 */
class CalculationsPandeoViewModelFactory(
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
        if (modelClass.isAssignableFrom(CalculationsPandeoViewModel::class.java)) {
            return CalculationsPandeoViewModel(lab) as T
        }
        throw IllegalArgumentException("Unknown CalculationsPandeoViewModel class")
    }
}