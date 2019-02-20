package uk.co.philharper.foodbudget.activity

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import uk.co.philharper.foodbudget.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        var editor = sharedPreferences.edit()
        editor.putFloat("WeeklyBudget", 50.0f)
        editor.putFloat("MonthlyBudget", (50.0f * 52.0f) / 12)
        editor.commit()
    }

    fun addShop(view: View) {
        val intent = Intent(this, AddShopActivity::class.java)
        startActivity(intent)
    }

    fun viewShops(view: View) {
        val intent = Intent(this, ViewShopActivity::class.java)
        startActivity(intent)
    }
}
