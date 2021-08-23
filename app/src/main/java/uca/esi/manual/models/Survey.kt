package uca.esi.manual.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Survey
 *
 * @property date
 * @property likert
 * @property like
 * @property learning
 * @property outside
 * @property suggestion
 * @constructor Create empty Survey
 */
@Parcelize
data class Survey(
    val date: String,
    var likert: MutableList<Int>,
    var like: Boolean = false,
    var learning: Boolean = false,
    var outside: Boolean = false,
    var suggestion: String = ""
) : Parcelable {
}
