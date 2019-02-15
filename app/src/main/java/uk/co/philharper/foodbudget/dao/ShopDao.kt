package uk.co.philharper.foodbudget.dao

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import uk.co.philharper.foodbudget.entity.Shop

class ShopDao {

    private val collection = "shops"

    fun saveShop(shop: Shop) {
        val db = FirebaseFirestore.getInstance()
        db.collection(collection).add(shop)
    }

    fun getShops(listener: (QuerySnapshot) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection(collection).get().addOnSuccessListener {
            querySnapshot ->  listener(querySnapshot)
        }
    }

}