package uca.esi.manual.database

import androidx.lifecycle.MutableLiveData
import uca.esi.manual.models.Survey
import uca.esi.manual.models.UserListResponse
import uca.esi.manual.models.labs.BaseLab

interface DatabaseHandlerI {
    fun getUserListFromDatabase(): MutableLiveData<UserListResponse>
    fun uploadData(lab: BaseLab)
    fun uploadData(survey: Survey)
}