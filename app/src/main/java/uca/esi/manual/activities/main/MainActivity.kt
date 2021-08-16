package uca.esi.manual.activities.main

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import uca.esi.manual.R
import uca.esi.manual.screens.ar.ARLauncherFragment
import uca.esi.manual.screens.calcs.data.CalculationsDataFragment
import uca.esi.manual.screens.chest.ChestFragment
import uca.esi.manual.screens.questionary.QuestionsFragment
import uca.esi.manual.screens.survey.dichotomic.DichotomicSurveyFragment
import uca.esi.manual.utils.currentNavigationFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onBackPressed() {
        when (val fragment = supportFragmentManager.currentNavigationFragment) {
            is ChestFragment,
            is ARLauncherFragment,
            is CalculationsDataFragment,
            is QuestionsFragment,
            is DichotomicSurveyFragment,
            -> Timber.i("Press back arrow on fragment $fragment")
            else -> super.onBackPressed()
        }
    }
}