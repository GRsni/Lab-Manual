package uca.esi.manual.screens.end

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EndViewModelFactory(private var allCorrect: Boolean, private val surveyDone: Boolean) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EndViewModel::class.java)) {
            return EndViewModel(allCorrect, surveyDone) as T
        }
        throw IllegalArgumentException("Unknown EndViewModel class")
    }
}