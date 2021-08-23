package uca.esi.manual.screens.end

import androidx.lifecycle.ViewModel
import uca.esi.manual.R
import uca.esi.manual.utils.ViewModelString

/**
 * End view model
 *
 * @constructor
 *
 * @param allCorrect
 * @param surveyDone
 */
class EndViewModel(allCorrect: Boolean, surveyDone: Boolean) : ViewModel() {

    val introString = getIntroText(allCorrect, surveyDone)

    val buttonNextString = getButtonString(surveyDone)

    /**
     * Get intro text
     *
     * @param allCorrect
     * @param surveyDone
     * @return
     */
    private fun getIntroText(allCorrect: Boolean, surveyDone: Boolean): ViewModelString {
        return if (surveyDone) {
            ViewModelString(R.string.texto_final_completo)
        } else {
            if (allCorrect) {
                ViewModelString(R.string.texto_final_bien)
            } else {
                ViewModelString(R.string.texto_final_mal)
            }
        }
    }

    /**
     * Get button string
     *
     * @param surveyDone
     * @return
     */
    private fun getButtonString(surveyDone: Boolean): ViewModelString {
        return if (surveyDone) {
            ViewModelString(R.string.boton_salir)
        } else {
            ViewModelString(R.string.boton_realizar_encuesta)
        }
    }
}