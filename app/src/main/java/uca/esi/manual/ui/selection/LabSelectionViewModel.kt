package uca.esi.manual.ui.selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LabSelectionViewModel : ViewModel() {

    lateinit var userId: String

    private val _inLab = MutableLiveData<Boolean>()
    val inLab: LiveData<Boolean>
        get() = _inLab

    init {
        userId = ""
        _inLab.value = true
    }
}