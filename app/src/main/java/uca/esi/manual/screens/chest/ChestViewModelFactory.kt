package uca.esi.manual.screens.chest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.labs.BaseLab

class ChestViewModelFactory(private val lab: BaseLab) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChestViewModel::class.java)) {
            return ChestViewModel(lab) as T
        }
        throw IllegalArgumentException("Unknown ChestViewModel class")
    }
}