package com.example.fooddeliver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.fooddeliver.Adapter.CategoryAdapter
import com.example.fooddeliver.Adapter.PopularAdapter
import com.example.fooddeliver.domain.CategoryDomain
import com.example.fooddeliver.domain.FoodDomain2
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var adapter2: RecyclerView.Adapter<*>
    private lateinit var recyclerViewCategoryList: RecyclerView
    private lateinit var recyclerViewPopularList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name = findViewById<TextView>(R.id.textView5)
        val trans = intent.getStringExtra("trans")
        if(trans != null){
            Toast.makeText(this, "Transaction Success", Toast.LENGTH_SHORT).show()
        }
        val loggedInUser = intent.getStringExtra("name")
        name.setText("Hi "+ loggedInUser)
        recyclerViewCategory()
        recyclerViewPopular()
        bottomNavigation(loggedInUser)
    }

    private fun bottomNavigation(user: String?) {
        val homeBtn = findViewById<LinearLayout>(R.id.homeBtn)
        val cartBtn = findViewById<LinearLayout>(R.id.cartBtn)
        val profileBtn = findViewById<LinearLayout>(R.id.profileBtn)

        homeBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity::class.java)
            intent.putExtra("name", user)
            startActivity(intent)
        }

        cartBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, CartActivity::class.java)
            intent.putExtra("name", user)
            startActivity(intent)
        }

        profileBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
            intent.putExtra("name", user)
            startActivity(intent)
        }
    }

    private fun recyclerViewPopular() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopularList = findViewById(R.id.view2)
        recyclerViewPopularList.layoutManager = linearLayoutManager

        val foodlist = ArrayList<FoodDomain2>()
        foodlist.add(FoodDomain2("Tteokbokki", "tteokbokki", "An authentic rice snack from korea", 16000.0, 5, 20, 2000))
        foodlist.add(FoodDomain2("Japchae", "jjapchae", "An authentic stach noodles from korea", 15000.0, 5, 15, 1500))
        foodlist.add(FoodDomain2("Ramyeon", "ramyeon", "An authentic noodle from korea", 16000.0, 5, 20, 2000))
        foodlist.add(FoodDomain2("Bibimbap", "bibimbap", "An authentic mixed topping rice from korea", 17000.0, 5, 15, 1500))
        foodlist.add(FoodDomain2("Kimchi", "kimchi", "KIMCHIII", 13000.0, 5, 5, 400))

        adapter2 = PopularAdapter(foodlist)
        recyclerViewPopularList.adapter = adapter2
    }

    private fun recyclerViewCategory() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategoryList = findViewById(R.id.view1)
        recyclerViewCategoryList.layoutManager = linearLayoutManager

        val foodlist = ArrayList<FoodDomain2>()
        foodlist.add(FoodDomain2("Banana Milk", "drink1", "An authentic banana flavored milk", 5000.0, 5, 1, 400))
        foodlist.add(FoodDomain2("Milkis", "drink2", "Milkis", 5000.0, 5, 1, 500))
        foodlist.add(FoodDomain2("Coffee", "drink3", "Coffee latte", 7000.0, 5, 10, 400))
        foodlist.add(FoodDomain2("Ice Cream", "dessert1", "Ice Creams with waffle", 11000.0, 5, 10, 700))
        foodlist.add(FoodDomain2("Mango Bingsu", "dessert2", "Authentic mango dessert from Korea", 13000.0, 5, 10, 600))

        adapter = PopularAdapter(foodlist)
        recyclerViewCategoryList.adapter = adapter
    }
}
