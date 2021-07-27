package uca.esi.manual

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import uca.esi.manual.screens.chest.ChestFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onBackPressed() {
        when (val fragment = supportFragmentManager.currentNavigationFragment) {
            is ChestFragment -> Timber.i("Press back arrow on fragment $fragment")
            else -> super.onBackPressed()
        }
    }
}