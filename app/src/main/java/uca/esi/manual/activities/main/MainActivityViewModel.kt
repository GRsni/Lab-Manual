package uca.esi.manual.activities.main

import androidx.lifecycle.ViewModel
import uca.esi.manual.models.ARModuleVariables

class MainActivityViewModel : ViewModel() {

    var arModule = ARModuleVariables(executed = false, solved = false)

}