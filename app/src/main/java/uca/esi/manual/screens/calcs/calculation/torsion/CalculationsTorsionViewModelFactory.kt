package uca.esi.manual.screens.calcs.calculation.torsion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.labs.BaseLab
import java.lang.IllegalArgumentException

/**
 * Calculations torsion view model factory
 *
 * @property lab
 * @constructor Create empty Calculations torsion view model factory
 */
class CalculationsTorsionViewModelFactory(
    private val lab: BaseLab
) : ViewModelProvider.Factory {

    /**
     * Create
     *
     * @param T
     * @param modelClass
     * @return
     */
    @Suppress("UNCHECkED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculationsTorsionViewModel::class.java)) {
            return CalculationsTorsionViewModel(lab) as T
        }
        throw IllegalArgumentException("Unknown CalculationsTorsionViewModel class")
    }
}