package uk.co.philharper.foodbudget.dao

import com.google.firebase.firestore.DocumentSnapshot
import uk.co.philharper.foodbudget.firebase.FirebaseConnection

class PropertiesDao {

    private val collection = "properties"

    private val firebase = FirebaseConnection().firestore

    fun getLocations(listener: (DocumentSnapshot) -> Unit) {

        firebase.collection(collection).document("kWdQ5g4g930wxHZzWi7R")
            .get().addOnSuccessListener { document -> listener(document) }
    }

}