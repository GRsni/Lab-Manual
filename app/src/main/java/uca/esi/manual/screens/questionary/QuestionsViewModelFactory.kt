package uca.esi.manual.screens.questionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.models.labs.BaseLab

class QuestionsViewModelFactory(private val lab: BaseLab) : ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionsViewModel::class.java)) {
            return QuestionsViewModel(lab) as T
        }
        throw IllegalArgumentException("Unknown QuestionsViewModel class")
    }
}