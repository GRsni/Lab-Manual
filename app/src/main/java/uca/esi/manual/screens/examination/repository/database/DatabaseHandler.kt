package uca.esi.manual.screens.examination.repository.database

import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import timber.log.Timber
import uca.esi.manual.models.labs.BaseLab

class DatabaseHandler : DatabaseHandlerI {

    private val rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val labsRef: DatabaseReference = rootRef.child("Users")

    override fun uploadData(lab: BaseLab) {
        Timber.i("Uploading lab: $lab")
        val path = createLabPath(lab)
        val key = labsRef.child(lab.userID).child(path).push().key

        val labUpdate = mapOf((path + key) to lab)

        labsRef.updateChildren(labUpdate) { databaseError: DatabaseError?, _: DatabaseReference? ->
            if (databaseError != null) {
                Timber.w(
                    "Data could not be saved to database: $databaseError.message"
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
}