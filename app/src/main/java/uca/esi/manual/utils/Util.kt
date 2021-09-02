package uca.esi.manual.utils

import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.common.hash.Hashing
import org.jetbrains.annotations.NotNull
import timber.log.Timber
import uca.esi.manual.BuildConfig
import uca.esi.manual.R
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.PandeoLab
import uca.esi.manual.models.labs.TorsionLab
import java.nio.charset.StandardCharsets
import kotlin.math.abs


/**
 * Get s h a256hashed string
 *
 * @param plain
 * @return
 */
@NotNull
fun getSHA256HashedString(plain: String): String {
    return Hashing.sha256().hashString(plain, StandardCharsets.UTF_8).toString()
}

val FragmentManager.currentNavigationFragment: Fragment?
    get() = primaryNavigationFragment?.childFragmentManager?.fragments?.first()

/**
 * Print lab
 *
 * @param lab
 */
fun printLab(lab: BaseLab) = when (lab.labType) {
    BaseLab.LabType.PANDEO -> Timber.i((lab as PandeoLab).toString())
    BaseLab.LabType.TORSION -> Timber.i((lab as TorsionLab).toString())
    else -> Timber.i(lab.toString())
}

/**
 * Print lab if debug
 *
 * @param lab
 */
fun printLabIfDebug(lab: BaseLab) {
    if (BuildConfig.DEBUG) {
        printLab(lab)
    }
}

/**
 * Is dark theme on
 *
 * @return true if dark theme is used
 */
fun Context.isDarkThemeOn(): Boolean {
    return resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
}

/**
 * Value not in threshold
 *
 * @param theoretical
 * @param experimental
 * @param thresholdPercent
 * @return
 */
fun valueNotInThreshold(
    theoretical: Float,
    experimental: Float,
    thresholdPercent: Float
): Boolean {
    return abs(theoretical - experimental) > abs(theoretical * thresholdPercent / 100)
}


/**
 * Show error dialog
 *
 * @param activity
 * @param titleId
 * @param messageId
 */
fun showErrorDialog(activity: Context, titleId: Int, messageId: Int) {
    AlertDialog.Builder(activity)
        .setTitle(titleId)
        .setMessage(messageId)
        .setNeutralButton(R.string.boton_aceptar, null)
        .show()
}