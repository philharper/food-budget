package uk.co.philharper.foodbudget.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import uk.co.philharper.foodbudget.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
