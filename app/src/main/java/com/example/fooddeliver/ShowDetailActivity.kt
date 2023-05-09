package com.example.fooddeliver

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fooddeliver.domain.FoodDomain2
import com.example.fooddeliver.Helper.ManagementCart

class ShowDetailActivity : AppCompatActivity() {
    private var addToCartBtn: TextView? = null
    private var titleTxt: TextView? = null
    private var feeTxt: TextView? = null
    private var descriptionTxt: TextView? = null
    private var numberOrderTxt: TextView? = null
    private var totalPriceTxt: TextView? = null
    private var starTxt: TextView? = null
    private var calorieTxt: TextView? = null
    private var timeTxt: TextView? = null
    private var plusBtn: ImageView? = null
    private var minusBtn: ImageView? = null
    private var picFood: ImageView? = null
    private var `object`: FoodDomain2? = null
    private var numberOrder = 1
    private var managementCart: ManagementCart? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)
        managementCart = ManagementCart(this)
        iniView()
        bundle
    }

    private val bundle: Unit
        private get() {
            `object` = intent.getSerializableExtra("object") as FoodDomain2?
            val drawableResourceId =
                this.resources.getIdentifier(`object`!!.pic, "drawable", this.packageName)
            picFood?.let {
                Glide.with(this)
                    .load(drawableResourceId)
                    .into(it)
            }
            titleTxt!!.text = `object`!!.title
            feeTxt!!.text = "$" + `object`!!.fee
            descriptionTxt!!.text = `object`!!.description
            numberOrderTxt!!.text = numberOrder.toString()
            calorieTxt!!.text = `object`!!.calories.toString() + " Calories"
            starTxt!!.text = `object`!!.star.toString() + ""
            timeTxt!!.text = `object`!!.time.toString() + " minutes"
            totalPriceTxt!!.text = "$" + Math.round(numberOrder * `object`!!.fee)
            plusBtn!!.setOnClickListener {
                numberOrder += 1
                numberOrderTxt!!.text = numberOrder.toString()
                totalPriceTxt!!.text = "$" + Math.round(numberOrder * `object`!!.fee)
            }
            minusBtn!!.setOnClickListener {
                if (numberOrder > 1) {
                    numberOrder -= 1
                }
                numberOrderTxt!!.text = numberOrder.toString()
                totalPriceTxt!!.text = "$" + Math.round(numberOrder * `object`!!.fee)
            }
            addToCartBtn!!.setOnClickListener {
                `object`!!.numberInCart = numberOrder
                managementCart!!.insertFood(`object`!!)
            }
        }

    private fun iniView() {
        addToCartBtn = findViewById(R.id.addToCartBtn)
        titleTxt = findViewById(R.id.titleTxt)
        feeTxt = findViewById(R.id.priceTxt)
        descriptionTxt = findViewById(R.id.descriptionTxt)
        numberOrderTxt = findViewById(R.id.numberItemTxt)
        plusBtn = findViewById(R.id.plusCardBtn)
        minusBtn = findViewById(R.id.minusCardBtn)
        picFood = findViewById(R.id.foodPic)
        totalPriceTxt = findViewById(R.id.totalPriceTxt)
        starTxt = findViewById(R.id.starTxt)
        calorieTxt = findViewById(R.id.caloriesTxt)
        timeTxt = findViewById(R.id.timeTxt)
    }
}