package uk.co.philharper.foodbudget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import uk.co.philharper.foodbudget.R
import uk.co.philharper.foodbudget.entity.Shop
import uk.co.philharper.foodbudget.service.ShopService
import java.text.SimpleDateFormat

class ShopListAdapter(private val shops: MutableList<Shop>) : RecyclerView.Adapter<ShopListAdapter.MyViewHolder>() {

    private val shopService = ShopService()
    lateinit var parent: ViewGroup

    class MyViewHolder(val constraintLayout: ConstraintLayout) : RecyclerView.ViewHolder(constraintLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListAdapter.MyViewHolder {
        val constraintLayout = LayoutInflater.from(parent.context).inflate(R.layout.shop_list_view, parent, false) as ConstraintLayout
        this.parent = parent
        return MyViewHolder(constraintLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val shop = shops.get(position)
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")

        holder.constraintLayout.findViewById<TextView>(R.id.recycler_view_date).text = simpleDateFormat.format(shop.date.toDate())
        holder.constraintLayout.findViewById<TextView>(R.id.recycler_view_location).text = shop.location
        holder.constraintLayout.findViewById<TextView>(R.id.shop_price).text = "£${shop.price}"
        holder.constraintLayout.findViewById<Button>(R.id.delete_shop_btn).setOnClickListener { deleteShop(position, shop) }
    }

    override fun getItemCount() = shops.size

    private fun deleteShop(position: Int, shop: Shop) {
        shops.remove(shop)
        shopService.deleteShop(shop)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, shops.size)
    }
}