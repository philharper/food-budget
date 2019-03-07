package uk.co.philharper.foodbudget.dao

import com.google.firebase.firestore.QuerySnapshot
import uk.co.philharper.foodbudget.firebase.FirebaseConnection
import uk.co.philharper.foodbudget.entity.Shop

class ShopDao {

    private val collection = "shops"

    private val firebase = FirebaseConnection().firestore

    fun saveShop(shop: Shop) {
        firebase.collection(collection).add(shop)
    }

    fun getShops(listener: (QuerySnapshot) -> Unit) {
        firebase.collection(collection).get().addOnSuccessListener {
            querySnapshot ->  listener(querySnapshot)
        }
    }

}