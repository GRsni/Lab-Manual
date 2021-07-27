package uca.esi.manual.screens.explanation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LabExplanationViewModelFactory(
    private val userId: String,
    private val inLab: Boolean,
    private val labType: Int
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LabExplanationViewModel::class.java)) {
            return LabExplanationViewModel(userId, inLab, labType) as T
        }
        throw IllegalArgumentException("Unknown LabExplanationViewModel class")
    }
}