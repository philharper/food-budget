package uk.co.philharper.foodbudget.activity

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import uk.co.philharper.foodbudget.R
import uk.co.philharper.foodbudget.dao.PropertiesDao
import uk.co.philharper.foodbudget.dao.ShopDao
import uk.co.philharper.foodbudget.entity.Properties
import uk.co.philharper.foodbudget.entity.Shop
import uk.co.philharper.foodbudget.service.ShopService
import java.text.SimpleDateFormat
import java.util.*

class AddShopActivity : AppCompatActivity() {

    private val shopService = ShopService()
    private val propertiesDao = PropertiesDao()

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

        propertiesDao.getLocations { document -> onSuccessListener(document) }
    }

    fun saveShop(view: View) {
        val location = findViewById<Spinner>(R.id.location_input).selectedItem
        val price = findViewById<TextView>(R.id.price_input).text.toString()
        val date = Timestamp(SimpleDateFormat("dd/MM/yyyy").parse(findViewById<TextView>(R.id.date_input).text.toString()))

        val shop = Shop(location.toString(),  price.toFloat(), date)

        shopService.saveShop(shop)
    }

    fun displayDatePicker(view: View) {
        val shopDatePicker = findViewById<DatePicker>(R.id.shop_date_picker)
        shopDatePicker.bringToFront()
        shopDatePicker.visibility = View.VISIBLE
    }

    private fun onSuccessListener(document: DocumentSnapshot) {
        val locationAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, document.toObject(Properties::class.java)?.locations)
        findViewById<Spinner>(R.id.location_input).adapter = locationAdapter
    }
}
