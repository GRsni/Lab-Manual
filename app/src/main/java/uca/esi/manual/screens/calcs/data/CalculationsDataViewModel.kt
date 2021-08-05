package uca.esi.manual.screens.calcs.data

import android.text.Spanned
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.screens.calcs.data.repository.CalcDataRepository
import uca.esi.manual.utils.ViewModelString

class CalculationsDataViewModel(
    labIN: BaseLab,
    var introText: ViewModelString,
) : ViewModel() {

    private val calcDataRepository = CalcDataRepository()

    private val _lab = MutableLiveData<BaseLab>()
    val lab: LiveData<BaseLab>
        get() = _lab

    var dataText: Spanned

    init {
        _lab.value = labIN
        dataText = calcDataRepository.getCalcDataString(labIN)
    }

}