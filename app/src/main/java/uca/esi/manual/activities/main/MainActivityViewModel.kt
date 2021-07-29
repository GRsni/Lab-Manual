package uca.esi.manual.activities.main

import androidx.lifecycle.ViewModel
import uca.esi.manual.models.ARModule

class MainActivityViewModel : ViewModel() {

    var arModule = ARModule(executed = false, solved = false)

}