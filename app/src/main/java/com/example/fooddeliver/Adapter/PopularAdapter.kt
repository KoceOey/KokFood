package com.example.fooddeliver.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliver.R
import com.example.fooddeliver.ShowDetailActivity
import com.example.fooddeliver.domain.FoodDomain2

class PopularAdapter(private val popularDomains: ArrayList<FoodDomain2>) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(
            R.layout.viewholder_popular,
            parent,
            false
        )
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = popularDomains[position].title
        holder.fee.text = "Rp." + popularDomains[position].fee.toString()
        val drawableResourceId = holder.itemView.context.resources.getIdentifier(
            popularDomains[position].pic,
            "drawable",
            holder.itemView.context.packageName
        )
        Glide.with(holder.itemView.context).load(drawableResourceId).into(holder.pic)
        holder.addBtn.setOnClickListener{
            val intent = Intent(holder.itemView.context, ShowDetailActivity::class.java);
            intent.putExtra("object", popularDomains[position]);
            holder.itemView.context.startActivity(intent);
        }
    }

    override fun getItemCount(): Int {
        return popularDomains.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title2)
        val fee: TextView = itemView.findViewById(R.id.fee)
        val pic: ImageView = itemView.findViewById(R.id.pic)
        val addBtn: ImageView = itemView.findViewById(R.id.addBtn)
    }
}
