package uca.esi.manual.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

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
