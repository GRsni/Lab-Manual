package uca.esi.manual.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import uca.esi.manual.models.UserListResponse
import uca.esi.manual.ui.login.repository.UsersRepository

class LoginViewModel : ViewModel() {

    private val repository: UsersRepository = UsersRepository()

    private val _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    private lateinit var response: LiveData<UserListResponse>

    init {
        response = getUserListResponse()
    }

    fun getUserListResponse(): LiveData<UserListResponse> {
        return repository.getResponseFromDatabase()
    }

    fun checkIdentfier() {
        if (response.value?.users != null) {
            if (!username.value.isNullOrEmpty() && !password.value.isNullOrEmpty()) {
                Timber.i("%s: -> %s", username.value, password.value)
            }
        }
    }

    private fun printResponse(response: UserListResponse) {
        response.users?.let { users ->
            users.forEach { user ->
                Timber.i("%s: %s", user.username, user.password)
            }
        }
    }

}