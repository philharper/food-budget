package uk.co.philharper.foodbudget.dao

import com.google.firebase.firestore.QuerySnapshot
import uk.co.philharper.foodbudget.entity.Shop
import uk.co.philharper.foodbudget.firebase.FirebaseConnection

class ShopDao {

    private val collection = "shops"

    private val firebase = FirebaseConnection().firestore

    fun saveShop(shop: Shop) {
        val documentReference = firebase.collection(collection).document()
        shop.id = documentReference.id

        documentReference.set(shop)
    }

    fun getShops(listener: (QuerySnapshot) -> Unit) {
        firebase.collection(collection).get().addOnSuccessListener {
            querySnapshot ->  listener(querySnapshot)
        }
    }

    fun deleteShop(shop: Shop) {
        firebase.collection(collection).document(shop.id).delete()
    }

}