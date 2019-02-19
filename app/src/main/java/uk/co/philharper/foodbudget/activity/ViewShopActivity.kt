package uk.co.philharper.foodbudget.activity

import android.icu.util.Currency
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import uk.co.philharper.foodbudget.R
import uk.co.philharper.foodbudget.service.ShopService
import java.util.*

class ViewShopActivity : AppCompatActivity() {

    val currency = Currency.getInstance(Locale.getDefault())

    private val shopService = ShopService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_shop)

        shopService.getShops { shopCalculation ->
            val totalSpent = "%.2f".format(shopCalculation.yearTotal)
            val currentWeekTotal = "%.2f".format(shopCalculation.currentWeekTotal)
            val currentMonthTotal = "%.2f".format(shopCalculation.currentMonthTotal)
            val weeklyAverage = "%.2f".format(shopCalculation.weeklyAverage)
            val monthlyAverage = "%.2f".format(shopCalculation.monthlyAverage)

            findViewById<TextView>(R.id.year_total_value).text = "${currency.symbol}$totalSpent"
            findViewById<TextView>(R.id.week_total_value).text = "${currency.symbol}$currentWeekTotal"
            findViewById<TextView>(R.id.month_total_value).text = "${currency.symbol}$currentMonthTotal"
            findViewById<TextView>(R.id.week_average_value).text = "${currency.symbol}$weeklyAverage"
            findViewById<TextView>(R.id.month_average_value).text = "${currency.symbol}$monthlyAverage"
        }

    }


}
