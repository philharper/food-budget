package uk.co.philharper.foodbudget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import uk.co.philharper.foodbudget.R
import uk.co.philharper.foodbudget.entity.Shop

class ShopListAdapter(private val shops: List<Shop>) : RecyclerView.Adapter<ShopListAdapter.MyViewHolder>() {

    class MyViewHolder(val relativeLayout: RelativeLayout) : RecyclerView.ViewHolder(relativeLayout)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListAdapter.MyViewHolder {
        val relativeLayout = LayoutInflater.from(parent.context).inflate(R.layout.shop_list_view, parent, false) as RelativeLayout

        return MyViewHolder(relativeLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val shop = shops.get(position)
        holder.relativeLayout.findViewById<TextView>(R.id.recycler_view_date).text = shop.location
        holder.relativeLayout.findViewById<TextView>(R.id.shop_price).text = "£${shop.price}"
    }

    override fun getItemCount() = shops.size
}