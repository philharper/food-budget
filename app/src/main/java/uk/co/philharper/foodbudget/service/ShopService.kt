package uk.co.philharper.foodbudget.service

import com.google.firebase.firestore.QuerySnapshot
import uk.co.philharper.foodbudget.dao.ShopDao
import uk.co.philharper.foodbudget.entity.Shop
import uk.co.philharper.foodbudget.entity.ShopCalculations

class ShopService {

    private val shopDao = ShopDao()
    var success: Boolean = false
    var shops = mutableListOf<Shop>()


    fun saveShop(shop: Shop) {
        shopDao.saveShop(shop)
    }

    fun getShops(callback: (ShopCalculations) -> Unit) {
        shopDao.getShops { querySnapshot ->  populateList(querySnapshot, callback) }
    }

    private fun populateList(document: QuerySnapshot, callback: (ShopCalculations) -> Unit) {
        shops = document.toObjects(Shop::class.java)
        val shopCalculation = ShopCalculations(shops)
        callback(shopCalculation)
    }

}