package uk.co.philharper.foodbudget.activity

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import uk.co.philharper.foodbudget.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.add_new_shop_btn).setOnClickListener { addShop() }
        findViewById<Button>(R.id.view_shops_btn).setOnClickListener { viewShops() }
        findViewById<Button>(R.id.shop_calculations_btn).setOnClickListener { viewShopAverages() }

        var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        var editor = sharedPreferences.edit()
        editor.putFloat("WeeklyBudget", 50.0f)
        editor.putFloat("MonthlyBudget", (50.0f * 52.0f) / 12)
        editor.commit()
    }

    private fun addShop() {
        val intent = Intent(this, AddShopActivity::class.java)
        startActivity(intent)
    }

    private fun viewShops() {
        val intent = Intent(this, ListShopsActivity::class.java)
        startActivity(intent)
    }

    private fun viewShopAverages() {
        val intent = Intent(this, ViewShopActivity::class.java)
        startActivity(intent)
    }
}
