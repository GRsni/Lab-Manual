package uca.esi.manual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uca.esi.manual.ui.main.MainFragment

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}