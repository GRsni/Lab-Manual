package uca.esi.manual.screens.materials.torsion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.labs.BaseLab

class MaterialsTorsionViewModelFactory(
    private val lab: BaseLab
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MaterialsTorsionViewModel::class.java)) {
            return MaterialsTorsionViewModel(lab) as T
        }
        throw IllegalArgumentException("Unknown MaterialsTorsionViewModel class")
    }
}