package uca.esi.manual.screens.calcs.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.R
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.PandeoLab
import uca.esi.manual.models.labs.TorsionLab
import uca.esi.manual.utils.ViewModelString

/**
 * Calculations data view model factory
 *
 * @property lab
 * @constructor Create empty Calculations data view model factory
 */
class CalculationsDataViewModelFactory(
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
        if (modelClass.isAssignableFrom(CalculationsDataViewModel::class.java)) {
            return when (lab) {
                is TorsionLab -> {
                    CalculationsDataViewModel(
                        lab,
                        ViewModelString(R.string.intro_calculos_torsion)
                    ) as T
                }
                is PandeoLab -> {
                    CalculationsDataViewModel(
                        lab,
                        ViewModelString(R.string.intro_calculos_pandeo)
                    ) as T
                }
                else -> throw IllegalArgumentException("Unknown CalculationsViewModel class")
            }
        }
        throw IllegalArgumentException("Unknown CalculationsViewModel class")
    }
}