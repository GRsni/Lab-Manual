package uca.esi.manual.ui.login.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import timber.log.Timber
import uca.esi.manual.models.UserListResponse

class UsersRepository {

    private val rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val usersRef: DatabaseReference = rootRef.child("Ids")

    fun getResponseFromDatabase(): MutableLiveData<UserListResponse> {
        Timber.i("loading users")
        val mutableData = MutableLiveData<UserListResponse>()
        usersRef.get().addOnCompleteListener { task ->
            val response = UserListResponse()
            if (task.isSuccessful) {
                val result = task.result
                response.users = result.value as MutableMap<String, String>
            } else {
                response.exception = task.exception
            }
            mutableData.value = response
        }
        return mutableData
    }

}