package uca.esi.manual.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.common.hash.Hashing
import org.jetbrains.annotations.NotNull
import timber.log.Timber
import uca.esi.manual.BuildConfig
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.PandeoLab
import uca.esi.manual.models.labs.TorsionLab
import java.nio.charset.StandardCharsets


@NotNull
fun getSHA256HashedString(plain: String): String {
    return Hashing.sha256().hashString(plain, StandardCharsets.UTF_8).toString()
}

val FragmentManager.currentNavigationFragment: Fragment?
    get() = primaryNavigationFragment?.childFragmentManager?.fragments?.first()

fun printLab(lab: BaseLab) = when (lab) {
    is PandeoLab -> Timber.i(lab.toString())
    is TorsionLab -> Timber.i(lab.toString())
    else -> Timber.i(lab.toString())
}

fun printLabIfDebug(lab: BaseLab) {
    if (BuildConfig.DEBUG) {
        printLab(lab)
    }
}

fun Context.isDarkThemeOn(): Boolean {
    return resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
}