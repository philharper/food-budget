package uk.co.philharper.foodbudget.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uk.co.philharper.foodbudget.R
import uk.co.philharper.foodbudget.service.ShopService

class MainActivity : AppCompatActivity() {

    private val currency = "£"
    private val shopService = ShopService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.add_new_shop_btn).setOnClickListener { addShop() }

        getData()
    }

    private fun getData() {
        shopService.getShopCalculations { shopCalculation ->
            val totalSpent = "%.2f".format(shopCalculation.yearTotal)
            val currentWeekTotal = "%.2f".format(shopCalculation.currentWeekTotal)
            val currentMonthTotal = "%.2f".format(shopCalculation.currentMonthTotal)

            findViewById<TextView>(R.id.year_total_value).text = "${currency}$totalSpent"
            findViewById<TextView>(R.id.week_total_value).text = "${currency}$currentWeekTotal"
            findViewById<TextView>(R.id.month_total_value).text = "${currency}$currentMonthTotal"

            val pieEntries = mutableListOf<PieEntry>()
            for (shopTotal in shopCalculation.shopTotals) {
                pieEntries.add(PieEntry(shopTotal.value, shopTotal.key))
            }
            val pieDataSet = PieDataSet(pieEntries, "");
            pieDataSet.colors = ColorTemplate.JOYFUL_COLORS.asList()

            val pieChart = findViewById<PieChart>(R.id.year_total_chart)
            pieChart.data = PieData(pieDataSet)
            pieChart.invalidate()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.properties_action -> {
            viewProperties()
            true
        }

        R.id.shops_action -> {
            viewShops()
            true
        }

        R.id.refresh_data -> {
            getData()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun addShop() {
        val intent = Intent(this, AddShopActivity::class.java)
        startActivity(intent)
    }

    private fun viewShops() {
        val intent = Intent(this, ListShopsActivity::class.java)
        startActivity(intent)
    }

    private fun viewProperties() {
        val intent = Intent(this, PropertiesActivity::class.java)
        startActivity(intent)
    }
}
