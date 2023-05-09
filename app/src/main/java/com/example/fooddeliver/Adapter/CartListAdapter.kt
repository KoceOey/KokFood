package com.example.fooddeliver.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliver.domain.FoodDomain2
import com.example.fooddeliver.Helper.ManagementCart
import com.example.fooddeliver.Interface.ChangeNumberItemsListener
import com.example.fooddeliver.R

class CartListAdapter(
    var listFoodSelected: ArrayList<FoodDomain2>,
    context: Context?,
    changeNumberItemsListener: ChangeNumberItemsListener
) :
    RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    private val managementCart: ManagementCart
    var changeNumberItemsListener: ChangeNumberItemsListener

    init {
        managementCart = ManagementCart(context!!)
        this.changeNumberItemsListener = changeNumberItemsListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = listFoodSelected[position].title
        holder.feeEachItem.text = "Rp." + listFoodSelected[position].fee
        holder.totalEachItem.text =
            "Rp." + Math.round(listFoodSelected[position].numberInCart * listFoodSelected[position].fee)
        holder.num.text = listFoodSelected[position].numberInCart.toString()
        val drawableResourceId = holder.itemView.context.resources.getIdentifier(
            listFoodSelected[position].pic, "drawable", holder.itemView.context.packageName
        )
        Glide.with(holder.itemView.context).load(drawableResourceId).into(holder.pic)
        holder.plusItem.setOnClickListener { view: View? ->
            managementCart.plusNumberFood(
                listFoodSelected, position,
                object : ChangeNumberItemsListener {
                    override fun changed() {
                        notifyDataSetChanged()
                        changeNumberItemsListener.changed()
                    }
                })
        }
        holder.minusItem.setOnClickListener { v: View? ->
            managementCart.minusNumberFood(
                listFoodSelected, position,
                object : ChangeNumberItemsListener {
                    override fun changed() {
                        notifyDataSetChanged()
                        changeNumberItemsListener.changed()
                    }
                })
        }
    }

    override fun getItemCount(): Int {
        return listFoodSelected.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var feeEachItem: TextView
        var pic: ImageView
        var plusItem: ImageView
        var minusItem: ImageView
        var totalEachItem: TextView
        var num: TextView

        init {
            title = itemView.findViewById(R.id.titleTxt)
            pic = itemView.findViewById(R.id.pic)
            feeEachItem = itemView.findViewById(R.id.feeEachItem)
            totalEachItem = itemView.findViewById(R.id.totalEachItem)
            plusItem = itemView.findViewById(R.id.plusCardBtn)
            minusItem = itemView.findViewById(R.id.minusCardBtn)
            num = itemView.findViewById(R.id.numberItemTxt)
        }
    }
}