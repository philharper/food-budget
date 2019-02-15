package uk.co.philharper.foodbudget.dao

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class PropertiesDao {

    private val collection = "properties"

    fun getLocations(listener: (DocumentSnapshot) -> Unit) {

        val db = FirebaseFirestore.getInstance()

        db.collection(collection).document("kWdQ5g4g930wxHZzWi7R")
            .get().addOnSuccessListener { document -> listener(document) }
    }

}