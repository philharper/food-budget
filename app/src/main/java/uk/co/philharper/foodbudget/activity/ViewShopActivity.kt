package uk.co.philharper.foodbudget.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uk.co.philharper.foodbudget.R
import uk.co.philharper.foodbudget.service.ShopService

class ViewShopActivity : AppCompatActivity() {

    private val shopService = ShopService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_shop)

        shopService.getShops { shopCalculation ->
            Toast.makeText(applicationContext, shopCalculation.totalSpent.toString(), Toast.LENGTH_LONG).show()
        }

    }


}
