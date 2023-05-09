package com.example.fooddeliver.Helper

import android.content.Context
import android.widget.Toast
import com.example.fooddeliver.domain.FoodDomain2
import com.example.fooddeliver.Interface.ChangeNumberItemsListener
import java.util.*

class ManagementCart(private val context: Context) {
    private val tinyDB: TinyDB = TinyDB(context)

    fun resetCart(){
        val listFood = ArrayList<FoodDomain2>()
        tinyDB.putListObject("CartList", listFood)
    }

    fun insertFood(item: FoodDomain2) {
        val listFood = listCart
        var existAlready = false
        var n = 0
        for (i in listFood.indices) {
            if (listFood[i].title == item.title) {
                existAlready = true
                n = i
                break
            }
        }
        if (existAlready) {
            listFood[n].numberInCart = item.numberInCart
        } else {
            listFood.add(item)
        }
        tinyDB.putListObject("CartList", listFood)
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()
    }

    val listCart: ArrayList<FoodDomain2>
        get() = tinyDB.getListObject("CartList")

    fun minusNumberFood(
        listfood: ArrayList<FoodDomain2>,
        position: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        if (listfood[position].numberInCart == 1) {
            listfood.removeAt(position)
        } else {
            listfood[position].numberInCart = listfood[position].numberInCart - 1
        }
        tinyDB.putListObject("CartList", listfood)
        changeNumberItemsListener.changed()
    }

    fun plusNumberFood(
        listfood: ArrayList<FoodDomain2>,
        position: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        listfood[position].numberInCart = listfood[position].numberInCart + 1
        tinyDB.putListObject("CartList", listfood)
        changeNumberItemsListener.changed()
    }

    val totalFee: Double
        get() {
            val listfood2 = listCart
            var fee = 0.0
            for (i in listfood2.indices) {
                fee += listfood2[i].fee * listfood2[i].numberInCart
            }
            return fee
        }
}
