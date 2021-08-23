package uca.esi.manual.screens.login

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import uca.esi.manual.database.DatabaseHandler
import uca.esi.manual.models.UserListResponse
import uca.esi.manual.utils.getSHA256HashedString

/**
 * Login view model
 *
 * @constructor Create empty Login view model
 */
class LoginViewModel : ViewModel() {

    private val dbHandler = DatabaseHandler()

    // The user id typed by the user
    private val _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username

    // The password typed by the user
    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    // The database response
    lateinit var response: LiveData<UserListResponse>

    // Event variable for empty user data handler
    private val _eventEmptyData = MutableLiveData<Boolean>()
    val eventEmptyData: LiveData<Boolean>
        get() = _eventEmptyData

    // Event variable for wrong user data handler
    private val _eventWrongData = MutableLiveData<Boolean>()
    val eventWrongData: LiveData<Boolean>
        get() = _eventWrongData

    // Event variable for correct user data handler
    private val _eventCorrectData = MutableLiveData<Boolean>()
    val eventCorrectData: LiveData<Boolean>
        get() = _eventCorrectData

    var isPasswordMasked = true

    init {
        _username.value = ""
        _password.value = ""
        readStudentList()
    }

    /**
     * Read student list
     *
     */
    private fun readStudentList() {
        Timber.i("Reading students from database")
        response = dbHandler.getUserListFromDatabase()
    }

    /**
     * Set username
     *
     * @param s
     */
    fun setUsername(s: Editable) {
        _username.value = s.toString()
    }

    /**
     * Set password
     *
     * @param s
     */
    fun setPassword(s: Editable) {
        _password.value = s.toString()
    }

    /**
     * Check identifier
     *
     */
    fun checkIdentifier() {
        if (response.value?.users != null) {
            if (!username.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) {
                Timber.i("%s: %s", username.value, password.value)
                if (keyMatchWithID(username.value.toString(), password.value.toString())) {
                    Timber.i("Found user in DB")
                    onCorrectData()
                } else {
                    Timber.i("User not in DB")
                    onWrongData()
                }
            } else {
                Timber.i("Empty username or password")
                onEmptyData()
            }
        }
    }

    /**
     * Change password mask
     *
     */
    fun changePasswordMask() {
        isPasswordMasked = !isPasswordMasked
    }

    /**
     * Key match with i d
     *
     * @param id
     * @param key
     * @return
     */
    private fun keyMatchWithID(id: String, key: String): Boolean {
        val hashKey = getSHA256HashedString(key)
        val keyInMap = response.value?.users?.get(id)
        return hashKey == keyInMap
    }

    /**
     * On empty data
     *
     */
    private fun onEmptyData() {
        _eventEmptyData.value = true
    }

    /**
     * On empty data complete
     *
     */
    fun onEmptyDataComplete() {
        _eventEmptyData.value = false
    }

    /**
     * On wrong data
     *
     */
    private fun onWrongData() {
        _eventWrongData.value = true
    }

    /**
     * On wrong data complete
     *
     */
    fun onWrongDataComplete() {
        _eventWrongData.value = false
    }

    /**
     * On correct data
     *
     */
    private fun onCorrectData() {
        _eventCorrectData.value = true
    }

    /**
     * On correct data complete
     *
     */
    fun onCorrectDataComplete() {
        _eventCorrectData.value = false
    }
}