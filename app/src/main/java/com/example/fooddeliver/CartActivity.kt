package com.example.fooddeliver

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliver.Adapter.CartListAdapter
import com.example.fooddeliver.Helper.ManagementCart
import com.example.fooddeliver.Interface.ChangeNumberItemsListener
import kotlin.math.log
import okhttp3.*
import java.io.IOException

class CartActivity : AppCompatActivity() {
    private var adapter: RecyclerView.Adapter<*>? = null
    private var recyclerViewList: RecyclerView? = null
    private var managementCart: ManagementCart? = null
    private var totalFeeTxt: TextView? = null
    private var taxTxt: TextView? = null
    private var deliveryTxt: TextView? = null
    private var totalTxt: TextView? = null
    private var emptyTxt: TextView? = null
    private var tax = 0.0
    private var scrollView: ScrollView? = null
    private var total: Double = 0.0
    private var client = OkHttpClient()
    private var url = "http://192.168.42.102:8080/transaction"
    private var username: String = "anonymous"
    private lateinit var formBody: FormBody

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        managementCart = ManagementCart(this)
        initView()
        initList()
        val loggedInUser = intent.getStringExtra("name")
        bottomNavigation(loggedInUser)
        calculateCard()
        initCheckOut(loggedInUser, this)
    }

    private fun initCheckOut(user: String?, context: Context){
        val checkOutBtn = findViewById<ConstraintLayout>(R.id.btnCheckOut)
        username = user ?:"anonymous"
        formBody = FormBody.Builder()
            .add("user", username)
            .add("price", total.toString()).build()
        checkOutBtn.setOnClickListener {
            val request = Request.Builder()
                .url(url)
                .post(formBody)
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("TAG", "Failed to execute request: ${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    managementCart = ManagementCart(context)
                    managementCart!!.resetCart()
                    val intent = Intent(this@CartActivity, MainActivity::class.java)
                    intent.putExtra("name", user)
                    intent.putExtra("trans","success")
                    startActivity(intent)
                }
            })
        }
    }

    private fun bottomNavigation(user: String?) {
        val homeBtn = findViewById<LinearLayout>(R.id.homeBtn)
        val cartBtn = findViewById<LinearLayout>(R.id.cartBtn)
        val profileBtn = findViewById<LinearLayout>(R.id.profileBtn)

        homeBtn.setOnClickListener {
            val intent = Intent(this@CartActivity, MainActivity::class.java)
            intent.putExtra("name", user)
            startActivity(intent)
        }

        cartBtn.setOnClickListener {
            val intent = Intent(this@CartActivity, CartActivity::class.java)
            intent.putExtra("name", user)
            startActivity(intent)
        }

        profileBtn.setOnClickListener {
            val intent = Intent(this@CartActivity, ProfileActivity::class.java)
            intent.putExtra("name", user)
            startActivity(intent)
        }
    }

    private fun initList() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewList!!.layoutManager = linearLayoutManager
        adapter =
            CartListAdapter(managementCart!!.listCart, this, object : ChangeNumberItemsListener {
                override fun changed() {
                    calculateCard()
                }
            })
        recyclerViewList!!.adapter = adapter
        if (managementCart!!.listCart.isEmpty()) {
            emptyTxt!!.visibility = View.VISIBLE
            scrollView!!.visibility = View.GONE
        } else {
            emptyTxt!!.visibility = View.GONE
            scrollView!!.visibility = View.VISIBLE
        }
    }

    private fun calculateCard() {
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round(managementCart!!.totalFee * percentTax * 100.0) / 100.0
        total = Math.round((managementCart!!.totalFee + tax + delivery) * 100.0) / 100.0
        val itemTotal = Math.round(managementCart!!.totalFee * 100.0) / 100.0
        totalFeeTxt!!.text = "Rp.$itemTotal"
        taxTxt!!.text = "Rp.$tax"
        deliveryTxt!!.text = "Rp.$delivery"
        totalTxt!!.text = "Rp.$total"
    }

    private fun initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt)
        taxTxt = findViewById(R.id.taxTxt)
        deliveryTxt = findViewById(R.id.deliveryTxt)
        totalTxt = findViewById(R.id.totalTxt)
        recyclerViewList = findViewById(R.id.view)
        scrollView = findViewById(R.id.scrollView3)
        emptyTxt = findViewById(R.id.emptyTxt)
    }
}