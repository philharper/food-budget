package uk.co.philharper.foodbudget

import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import uk.co.philharper.foodbudget.dao.ShopDao
import uk.co.philharper.foodbudget.entity.Shop

class AddShopActivity : AppCompatActivity() {

    private val shopDao = ShopDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_shop)
    }

    fun saveShop(view: View) {
        val location = findViewById<Spinner>(R.id.location_input).selectedItem
        val price = findViewById<TextView>(R.id.price_input).text.toString()
        val date = findViewById<TextView>(R.id.date_input).text

        val shop = Shop(location.toString(),  price.toFloat(), date.toString())

        shopDao.saveShop(shop)
    }
}
