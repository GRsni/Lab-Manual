package uca.esi.manual.screens.materials.torsion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.labs.BaseLab

/**
 * Materials torsion view model factory
 *
 * @property lab
 * @constructor Create empty Materials torsion view model factory
 */
class MaterialsTorsionViewModelFactory(
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
        if (modelClass.isAssignableFrom(MaterialsTorsionViewModel::class.java)) {
            return MaterialsTorsionViewModel(lab) as T
        }
        throw IllegalArgumentException("Unknown MaterialsTorsionViewModel class")
    }
}