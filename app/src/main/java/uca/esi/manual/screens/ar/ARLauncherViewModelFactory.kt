package uca.esi.manual.screens.ar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uca.esi.manual.R
import uca.esi.manual.models.ARResourceIds
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.models.labs.PandeoLab
import uca.esi.manual.models.labs.TorsionLab

/**
 * A r launcher view model factory
 *
 * @property lab
 * @constructor Create empty A r launcher view model factory
 */
class ARLauncherViewModelFactory(
    private val lab: BaseLab
) : ViewModelProvider.Factory {

    /**
     * Create
     *
     * @param T
     * @param modelClass
     * @return
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ARLauncherViewModel::class.java)) {
            return ARLauncherViewModel(lab, getResourceIds(lab)) as T
        }
        throw IllegalArgumentException("Unknown ARLauncherViewModel class")
    }

    /**
     * Get resource ids
     *
     * @param lab
     * @return
     */
    private fun getResourceIds(lab: BaseLab): ARResourceIds {
        return when (lab) {
            is PandeoLab -> {
                if (lab.isInLab) {
                    ARResourceIds(
                        R.string.prepara_maquina_pandeo_lab,
                        R.string.movil_posicion_vertical,
                        R.drawable.posicion_qr_pandeo_lab
                    )
                } else {
                    ARResourceIds(
                        R.string.prepara_maquina_pandeo_casa,
                        R.string.movil_posicion_vertical,
                        R.drawable.qr_mesa
                    )
                }
            }
            is TorsionLab -> {
                if (lab.isInLab) {
                    ARResourceIds(
                        R.string.prepara_maquina_torsion_lab,
                        R.string.movil_posicion_horizontal,
                        R.drawable.posicion_qr_torsion_lab
                    )
                } else {
                    ARResourceIds(
                        R.string.prepara_maquina_torsion_casa,
                        R.string.movil_posicion_horizontal,
                        R.drawable.qr_mesa
                    )
                }
            }
            else -> ARResourceIds(
                R.string.error_datos_practicas,
                R.string.error_datos_practicas,
                R.drawable.qr_mesa
            )
        }
    }
}