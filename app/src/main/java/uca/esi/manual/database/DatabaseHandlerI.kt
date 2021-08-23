package uca.esi.manual.database

import androidx.lifecycle.MutableLiveData
import uca.esi.manual.models.Survey
import uca.esi.manual.models.UserListResponse
import uca.esi.manual.models.labs.BaseLab

/**
 * Database handler i
 *
 * @constructor Create empty Database handler i
 */
interface DatabaseHandlerI {
    /**
     * Get user list from database
     *
     * @return
     */
    fun getUserListFromDatabase(): MutableLiveData<UserListResponse>

    /**
     * Upload data
     *
     * @param lab
     */
    fun uploadData(lab: BaseLab)

    /**
     * Upload data
     *
     * @param survey
     */
    fun uploadData(survey: Survey)
}