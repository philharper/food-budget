package uk.co.philharper.foodbudget.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.co.philharper.foodbudget.R
import uk.co.philharper.foodbudget.adapter.ShopListAdapter
import uk.co.philharper.foodbudget.entity.Shop
import uk.co.philharper.foodbudget.service.ShopService

class ListShopsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val shopService = ShopService();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_shops)

        shopService.getShops { shops -> populateShops(shops) }

        viewManager = LinearLayoutManager(this)

    }

    private fun populateShops(shops: List<Shop>) {
        viewAdapter = ShopListAdapter(shops, deleteShopCallBack = notifyShopDeletion())

        recyclerView = findViewById<RecyclerView>(R.id.list_shops_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun notifyShopDeletion(): () -> Unit {
        return {
            Toast.makeText(this, "Shop Deleted", Toast.LENGTH_LONG)
        }
    }
}
