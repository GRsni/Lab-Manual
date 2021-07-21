package uca.esi.manual.ui.login.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import timber.log.Timber
import uca.esi.manual.models.User
import uca.esi.manual.models.UserListResponse

class UsersRepository {

    private val rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val usersRef: DatabaseReference = rootRef.child("Ids")

    fun getResponseFromDatabase(): MutableLiveData<UserListResponse> {
        Timber.i("loading users")
        val data = MutableLiveData<UserListResponse>()
        usersRef.get().addOnCompleteListener { task ->
            val response = UserListResponse()
            if (task.isSuccessful) {
                Timber.i("task successful, retrieved user ids")
                val result = task.result
                result.let {
                    Timber.i("children: %s", result.hasChildren())
                    response.users = result.children.map {
                        User(it.key, it.value.toString())
                    }
                }
            } else {
                response.exception = task.exception
            }
            data.value = response
        }
        return data
    }

}