package uca.esi.manual.screens.materials.weights

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.utils.printLabIfDebug

class WeightsViewModelFactory(
    private val lab: BaseLab
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeightsViewModel::class.java)) {
            return WeightsViewModel(lab) as T
        }
        throw IllegalArgumentException("Unknown WeightsViewModel class")
    }
}