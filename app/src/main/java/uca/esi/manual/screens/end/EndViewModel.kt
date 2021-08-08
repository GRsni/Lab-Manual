package uca.esi.manual.screens.end

import androidx.lifecycle.ViewModel
import uca.esi.manual.R
import uca.esi.manual.utils.ViewModelString

class EndViewModel(allCorrect: Boolean) : ViewModel() {

    val introString = getIntroText(allCorrect)

    private fun getIntroText(allCorrect: Boolean): ViewModelString {
        return if (allCorrect) {
            ViewModelString(R.string.texto_final_bien)
        } else {
            ViewModelString(R.string.texto_final_mal)
        }
    }

}