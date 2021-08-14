package uca.esi.manual.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Survey(
    val date: Date,
    var likert: IntArray,
    var like: Boolean = false,
    var learning: Boolean = false,
    var outside: Boolean = false,
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Survey
        if (date != other.date) return false
        if (like != other.like) return false
        if (learning != other.learning) return false
        if (outside != other.outside) return false
        if (!likert.contentEquals(other.likert)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = date.hashCode()
        result = 31 * result + like.hashCode()
        result = 31 * result + learning.hashCode()
        result = 31 * result + outside.hashCode()
        result = 31 * result + likert.contentHashCode()
        return result
    }

}
