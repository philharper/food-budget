package uk.co.philharper.foodbudget.dao

import com.google.firebase.firestore.QuerySnapshot
import uk.co.philharper.foodbudget.entity.Properties
import uk.co.philharper.foodbudget.firebase.FirebaseConnection

class PropertiesDao {

    private val collection = "properties"

    private val firebase = FirebaseConnection().firestore

    fun saveProperties(properties: Properties) {
        val documentReference = firebase.collection(collection).document()
        properties.id = documentReference.id

        documentReference.set(properties)
    }

    fun getProperties(listener: (QuerySnapshot) -> Unit) {
        firebase.collection(collection).get().addOnSuccessListener {
            querySnapshot ->  listener(querySnapshot)
        }
    }

}