package uca.esi.manual.screens.end

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class EndViewModelFactory(private var allCorrect: Boolean) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EndViewModel::class.java)){
            return EndViewModel(allCorrect) as T
        }
        throw IllegalArgumentException("Unknown EndViewModel class")
    }
}