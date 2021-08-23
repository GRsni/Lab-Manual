package uca.esi.manual.screens.survey.dichotomic

import androidx.lifecycle.ViewModel
import uca.esi.manual.models.Survey

/**
 * Dichotomic survey view model
 *
 * @property survey
 * @constructor Create empty Dichotomic survey view model
 */
class DichotomicSurveyViewModel(val survey: Survey) : ViewModel() {


    /**
     * On like switch click
     *
     * @param value
     */
    fun onLikeSwitchClick(value: Boolean) {
        survey.like = value
    }

    /**
     * On learn switch click
     *
     * @param value
     */
    fun onLearnSwitchClick(value: Boolean) {
        survey.learning = value
    }

    /**
     * On outside switch click
     *
     * @param value
     */
    fun onOutsideSwitchClick(value: Boolean) {
        survey.outside = value
    }
}