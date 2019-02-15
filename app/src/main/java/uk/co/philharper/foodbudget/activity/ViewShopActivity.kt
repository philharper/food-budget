package uk.co.philharper.foodbudget.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.QuerySnapshot
import uk.co.philharper.foodbudget.R
import uk.co.philharper.foodbudget.dao.ShopDao
import uk.co.philharper.foodbudget.entity.Shop

class ViewShopActivity : AppCompatActivity() {

    private val shopDao = ShopDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_shop)

        shopDao.getShops { document -> populateList(document) }
    }

    private fun populateList(document: QuerySnapshot) {
        val shops = document.toObjects(Shop::class.java)


    }
}
