package uca.esi.manual.screens.calcs.data

import android.text.Spanned
import androidx.lifecycle.ViewModel
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.screens.calcs.data.repository.CalcDataRepository
import uca.esi.manual.utils.ViewModelString

class CalculationsDataViewModel(
    var lab: BaseLab,
    var introText: ViewModelString,
) : ViewModel() {

    private val calcDataRepository = CalcDataRepository()

    var dataText: Spanned = calcDataRepository.getCalcDataString(lab)

}