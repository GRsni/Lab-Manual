package uca.esi.manual.screens.materials.pandeo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.labs.BaseLab

class MaterialsPandeoViewModelFactory(
    private val lab: BaseLab
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MaterialsPandeoViewModel::class.java)) {
            return MaterialsPandeoViewModel(lab) as T
        }
        throw IllegalArgumentException("Unknown MaterialsPandeoViewModel class")
    }
}