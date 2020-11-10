package uk.co.philharper.foodbudget.activity

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import uk.co.philharper.foodbudget.R
import uk.co.philharper.foodbudget.entity.Properties
import uk.co.philharper.foodbudget.entity.Shop
import uk.co.philharper.foodbudget.service.PropertiesService
import uk.co.philharper.foodbudget.service.ShopService
import java.text.SimpleDateFormat
import java.util.*


class AddShopActivity : AppCompatActivity() {

    private val shopService = ShopService()
    private val propertiesService = PropertiesService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_shop)

        findViewById<Button>(R.id.select_date_btn).setOnClickListener { displayDatePicker() }
        findViewById<Button>(R.id.save_shop_btn).setOnClickListener { saveShop() }

        val calendar = Calendar.getInstance()

        val shopDatePicker = findViewById<DatePicker>(R.id.shop_date_picker)
        shopDatePicker.init(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                DatePicker.OnDateChangedListener { datePicker, year, month, day ->
                    findViewById<EditText>(R.id.date_input).setText("$day/${month + 1}/$year")
                    datePicker.visibility = View.GONE
                })

        propertiesService.getProperties { document -> populateProperties(document) }
    }

    private fun saveShop() {
        val location = findViewById<Spinner>(R.id.location_input).selectedItem
        val price = findViewById<TextView>(R.id.price_input).text.toString()
        val date = Timestamp(SimpleDateFormat("dd/MM/yyyy").parse(findViewById<EditText>(R.id.date_input).text.toString()))

        val shop = Shop(location.toString(), price.toFloat(), date)

        shopService.saveShop(shop)

        Toast.makeText(this, "Shop Saved", Toast.LENGTH_LONG).show();

        finish()
    }

    private fun displayDatePicker() {
        val shopDatePicker = findViewById<DatePicker>(R.id.shop_date_picker)
        shopDatePicker.bringToFront()
        shopDatePicker.visibility = View.VISIBLE
        hideKeyboard()
    }

    private fun populateProperties(properties: List<Properties>) {
        val locationAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, properties[0].locations)
        findViewById<Spinner>(R.id.location_input).adapter = locationAdapter
    }

    private fun hideKeyboard() {
        val inputMethodManager: InputMethodManager = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val view: View? = this.currentFocus;
        if (view != null)
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
