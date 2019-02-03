package uk.co.philharper.foodbudget

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import uk.co.philharper.foodbudget.dao.ShopDao
import uk.co.philharper.foodbudget.entity.Shop
import java.util.*

class AddShopActivity : AppCompatActivity() {

    private val shopDao = ShopDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_shop)

        val calendar = Calendar.getInstance()

        val datePicker = findViewById<DatePicker>(R.id.shop_date_picker)
        datePicker.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            DatePicker.OnDateChangedListener { datePicker, year, month, day ->
                findViewById<EditText>(R.id.date_input).setText("$day/$month/$year")
                datePicker.visibility = View.GONE
            })
    }

    fun saveShop(view: View) {
        val location = findViewById<Spinner>(R.id.location_input).selectedItem
        val price = findViewById<TextView>(R.id.price_input).text.toString()
        val date = findViewById<TextView>(R.id.date_input).text

        val shop = Shop(location.toString(),  price.toFloat(), date.toString())

        shopDao.saveShop(shop)
    }

    fun displayDatePicker(view: View) {
        val shopDatePicker = findViewById<DatePicker>(R.id.shop_date_picker)
        shopDatePicker.bringToFront()
        shopDatePicker.visibility = View.VISIBLE
    }
}
