package uca.esi.manual.screens.survey.dichotomic

import androidx.lifecycle.ViewModel
import uca.esi.manual.models.Survey

class DichotomicSurveyViewModel(val survey: Survey) : ViewModel() {


    fun onLikeSwitchClick(value: Boolean) {
        survey.like = value
    }

    fun onLearnSwitchClick(value: Boolean) {
        survey.learning = value
    }

    fun onOutsideSwitchClick(value: Boolean) {
        survey.outside = value
    }
}