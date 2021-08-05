package uca.esi.manual.screens.calcs.calculation.pandeo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.labs.BaseLab

class CalculationsPandeoViewModelFactory(
    private val lab: BaseLab
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculationsPandeoViewModel::class.java)) {
            return CalculationsPandeoViewModel(lab) as T
        }
        throw IllegalArgumentException("Unknown CalculationsPandeoViewModel class")
    }
}