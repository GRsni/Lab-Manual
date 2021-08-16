package uca.esi.manual.database

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import timber.log.Timber
import uca.esi.manual.models.Survey
import uca.esi.manual.models.UserListResponse
import uca.esi.manual.models.labs.BaseLab

class DatabaseHandler : DatabaseHandlerI {

    private val rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val usersRef: DatabaseReference = rootRef.child("Ids")
    private val labsRef: DatabaseReference = rootRef.child("Users")
    private val surveysRef: DatabaseReference = rootRef.child("Surveys")

    @Suppress("UNCHECKED_CAST")
    override fun getUserListFromDatabase(): MutableLiveData<UserListResponse> {
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


    override fun uploadData(lab: BaseLab) {
        Timber.i("Uploading lab: $lab")
        val path = createLabPath(lab)
        val key = labsRef.child(lab.userID).child(path).push().key

        val labUpdate = mapOf((path + key) to lab)

        labsRef.updateChildren(labUpdate) { databaseError: DatabaseError?, _: DatabaseReference? ->
            if (databaseError != null) {
                Timber.w(
                    "Data could not be saved to database: ${databaseError.message}"
                )
            } else {
                Timber.d("Data saved succesfully")
            }
        }
    }

    private fun createLabPath(lab: BaseLab): String {
        return "/" + lab.userID + "/practicas/" +
                when (lab.labType) {
                    BaseLab.LabType.TORSION -> "torsion/"
                    BaseLab.LabType.PANDEO -> "pandeo/"
                    else -> "error"
                }
    }

    override fun uploadData(survey: Survey) {
        Timber.i("Uploading survey: $survey")
        val key = surveysRef.push().key

        val surveyUpdate = mapOf(key to survey)

        surveysRef.updateChildren(surveyUpdate) { databaseError: DatabaseError?, _: DatabaseReference? ->
            if (databaseError != null) {
                Timber.w("Data could not be saved to database: ${databaseError.message}")
            } else {
                Timber.d("Data saved successfully")
            }
        }
    }
}