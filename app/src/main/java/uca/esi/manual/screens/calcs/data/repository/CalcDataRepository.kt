package uca.esi.manual.screens.calcs.data.repository

import android.text.Spanned
import androidx.core.text.HtmlCompat
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.PandeoLab
import uca.esi.manual.models.labs.TorsionLab

/**
 * Calc data repository
 *
 * @constructor Create empty Calc data repository
 */
class CalcDataRepository {

    private val torsionData =
        "<div>Estos son los valores que se te han asignado para esta práctica:</div>" +
                "<span><b>&emsp;Carga en N: %1 N</b></span>" +
                "<div>Módulo de resistencia: </div>" +
                "<span><b>&emsp;Wp = 196.3 mm<sup>3</sup></b></span>" +
                "<div>Módulo de Elasticidad Transversal:</div>" +
                "<span><b>&emsp;G = 80 000 N/mm<sup>2</sup></b></span>" +
                "<div>Coeficiente de la galga extensiométrica:</div>" +
                "<span><b>&emsp;k = 2.05</b></span>"

    private val pandeoData =
        "<div>Estos son los valores que se te ha asignado para esta práctica:</div>\n" +
                "<span><b>&emsp;Barra de prueba: S2 Acero</b></span><br>\n" +
                "<span><b>&emsp;Longitud (Lp): %1 mm</b></span>\n" +
                "<div>Momento de inercia de superficie:</div>\n" +
                "<span><b>&emsp;I<sub>min</sub> = 106.6 mm<sup>4</sup></b></span>\n" +
                "<div>Módulo de elasticidad:</div>\n" +
                "<span><b>&emsp;E = 210 000 N/mm<sup>2</sup></b></span>"

    private val errorString = "Error cargando los datos para la práctica"


    /**
     * Get calc data string
     *
     * @param lab
     * @return
     */
    fun getCalcDataString(lab: BaseLab): Spanned {
        return when (lab) {
            is TorsionLab -> HtmlCompat.fromHtml(
                torsionData.replace(
                    "%1",
                    lab.weights.toString()
                ),
                HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH
            )
            is PandeoLab
            -> HtmlCompat.fromHtml(
                pandeoData.replace(
                    "%1",
                    lab.bar.toString()
                ),
                HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH
            )
            else -> HtmlCompat.fromHtml(
                errorString,
                HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH
            )
        }
    }
}