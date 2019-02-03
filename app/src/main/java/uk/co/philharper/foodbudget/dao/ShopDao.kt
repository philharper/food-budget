package uk.co.philharper.foodbudget.dao

import com.google.firebase.firestore.FirebaseFirestore
import uk.co.philharper.foodbudget.entity.Shop

class ShopDao {

    private val collection = "shops"

    fun saveShop(shop: Shop) {
        val db = FirebaseFirestore.getInstance()
        db.collection(collection).add(shop)
    }

}