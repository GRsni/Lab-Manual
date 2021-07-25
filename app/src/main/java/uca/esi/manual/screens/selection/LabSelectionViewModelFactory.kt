package uca.esi.manual.screens.selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LabSelectionViewModelFactory(private val userId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LabSelectionViewModel::class.java)) {
            return LabSelectionViewModel(userId) as T
        }
        throw IllegalArgumentException("Unknown LabSelectionViewModel class")
    }
}